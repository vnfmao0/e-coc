let uploadFiles = [];
let ConvertFileSizeUnit = function (size) {
    var nSize = 0;
    var sUnit = "Byte";

    nSize = size;
    if (nSize >= 1024) {
        nSize = nSize / 1024;
        sUnit = "KB";
    }
    if (nSize >= 1024) {
        nSize = nSize / 1024;
        sUnit = "MB";
    }
    if (nSize >= 1024) {
        nSize = nSize / 1024;
        sUnit = "GB";
    }
    if (nSize >= 1024) {
        nSize = nSize / 1024;
        sUnit = "TB";
    }
    //sReturn = (Math.round(nSize) + (Math.round(nSize) - nSize)).toFixed(1) + sUnit;
    let sReturn = parseFloat(nSize).toFixed(3) + sUnit;

    return sReturn;
};
let makePreviewList = function (file, idx) {
    console.log("makePreviewList!!!")
    console.log(file.name + " / " + ConvertFileSizeUnit(file.size));
    // let tbody = document.querySelector(".dz-file-list .file-list-area");
    // let tr = document.createElement("tr");
    // let tdFileName =document.createElement("td");
    // let tdFileSize =document.createElement("td");
    // let tdBtn =document.createElement("td");
    // tdFileName.textContent = file.name;
    // tdFileSize.textContent = ConvertFileSizeUnit(file.size);
    // tdBtn.innerHTML = "삭제";
    // //tbody.innerHTML = "<tr><td>123</td><td>123</td><td>123</td></tr>";
    // tr.appendChild(tdFileName);
    // tr.appendChild(tdFileSize);
    // tr.appendChild(tdBtn);
    // tbody.appendChild(tr);


    // <div className="row">
    //     <div className="col-12">
    //         <div className="row">
    //             <div className="col-4 m-b-5">
    //                 <div className="input-group">
    //                     <input className="form-control" type="text" readOnly value="filename(filesize)">
    //                                                             <span className="input-group-text">
    //                                                                 <i className="icon-trash  fs-5 text-danger"></i>
    //                                                             </span>
    //                 </div>
    //             </div>
    //         </div>
    //     </div>
    // </div>
    let i = document.createElement("i");
    i.setAttribute("class", "icon-trash  fs-5 text-danger");
    i.setAttribute("onclick", "deleteUploadFile(" + (uploadFiles.length -1) + ")");
    let span = document.createElement("span");
    span.setAttribute("class", "input-group-text");
    span.appendChild(i);
    let input = document.createElement("input");
    input.setAttribute("type", "text");
    input.setAttribute("class", "form-control");
    input.setAttribute("readonly", "true");
    input.value = file.name + "(" + ConvertFileSizeUnit(file.size) + ")";

    let divGrp = document.createElement("div");
    divGrp.setAttribute("class", "input-group");
    divGrp.appendChild(input);
    divGrp.appendChild(span);
    let divCol4 = document.createElement("div");
    divCol4.setAttribute("id", "upfile-idx-" + (uploadFiles.length -1));
    divCol4.setAttribute("file-idx", "" + (uploadFiles.length -1));
    divCol4.setAttribute("class", "col-4 m-b-5");
    divCol4.appendChild(divGrp);
    let fileArea = document.querySelector(".dz-file-list");
    fileArea.appendChild(divCol4);

};
let deleteUploadFile = function (fileIdx) {

    //uploadFiles.splice(fileIdx, 1);   //uploadFiles 배열 사이즈 변경. 해당 index 자체를 지운다.
    delete uploadFiles[fileIdx];    //uploadFiles 배열 사이즈 변경안됨. 해당 index의 값만 비운다.

    //화면상에서 파일 목록 삭제
    let rm = document.querySelector("#upfile-idx-" + fileIdx);
    rm.parentNode.removeChild(rm);
};
let deleteFileByFileSeq = function (fileSeq, removeTrgt) {
    if (fileSeq == "") {
        toastrWarning("파일 아이디가 없습니다.");
        return;
    }
    if(!confirm("게시물을 저장하지 않더라도 파일은 삭제됩니다.\n파일을 삭제하시겠습니까?")) return;

    let params = {};
    params.fileSeq = fileSeq;

    ajaxJsonCall("/cmmfile/deleteFile/" + fileSeq, params, function (data) {
        if (data.fields.RESULT_CD == "S") {
            //화면의 파일 목록에서 해당 파일 삭제
            let rm = document.querySelector("#" + removeTrgt);
            rm.parentNode.removeChild(rm);

            toastrSuccess();
        } else {
            toastrError(data.errMsg);
        }
    });
};

var DropzoneFileUploadProc = function (parentId, menuId, info1, info2, info3, info4, info5) {
    console.log("DropzoneFileUploadProc");
    console.log(uploadFiles);

    let formData = new FormData();
    formData.append("parentId", parentId);
    formData.append("menuId", menuId);
    formData.append("info1", info1);
    formData.append("info2", info2);
    formData.append("info3", info3);
    formData.append("info4", info4);
    formData.append("info5", info5);

    //uploadFiles
    for (let i = 0; i < uploadFiles.length; i++) {
        if(uploadFiles[i] === undefined) continue;
        formData.append("upfiles", uploadFiles[i]);
    }

    if (formData.getAll("upfiles").length == 0) {
        //toastrWarning("업로드할 파일이 없습니다.");
        return;
    }

    $.ajax({
        enctype: 'multipart/form-data',
        url: '/cmmfile/multiFileupload',
        data: formData,
        type: 'post',
        contentType: false,
        processData: false,
        xhr: function () { //XMLHttpRequest 재정의 가능
            var xhr = $.ajaxSettings.xhr();
            xhr.upload.onprogress = function (e) { //progress 이벤트 리스너 추가
                //console.log("groupUpload.progress");
                var percent = e.loaded * 100 / e.total;
                //setProgress(percent);
            };
            return xhr;
        },
        success: function (ret) {
            console.log("success");
            console.log(ret);
            //uploadedFileInfo = ret.fields.FILE_INFOS;
            //게시물 등록
            //regist();
            //alert("완료");
        }
    });
};

var DropzoneFileUpload = function () {
    var DropzoneUpload = function () {
        Dropzone.options.singleFileUpload = {
            paramName: "upfiles",
            maxFiles: 1, // 업로드 파일수
            maxFilesize: 10, // 최대업로드용량 : 10MB
            autoProcessQueue: false, // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
            clickable: true, // 클릭가능여부
            thumbnailHeight: 90, // Upload icon size
            thumbnailWidth: 90, // Upload icon size
            parallelUploads: 99, // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
            addRemoveLinks: true, // 삭제버튼 표시 여부
            dictRemoveFile: '삭제', // 삭제버튼 표시 텍스트
            uploadMultiple: true, // 다중업로드 기능
            accept: function(file, done) {
                console.log("accept()1")
                if (file.name == "justinbieber.jpg" || file.name == "20211203수정요청건.PNG") {
                    done("Naha, you don't.");
                } else {
                    done();
                }
            },
            success: function (file, response) {
                console.log("success");
                console.log(file);
                console.log(response);
            },
            error: function (file, response) {
                console.log("error");
                console.log(response);
            },
            addedfile: function (file) {
                console.log("addedfile");
                console.log(file);
                uploadFiles.push(file);

                makePreviewList(file, 0);
            }
        };
        Dropzone.options.multiFileUpload = {
            paramName: "upfiles",
            //url: "/cmmfile/singleFileUpload",
            maxFiles: 10,
            maxFilesize: 10,
            autoProcessQueue: false, // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
            clickable: true, // 클릭가능여부
            createImageThumbnails: false,   //이미지의 썸네일 생성 여부
            thumbnailHeight: 90, // Upload icon size
            thumbnailWidth: 90, // Upload icon size
            parallelUploads: 99, // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
            addRemoveLinks: true, // 삭제버튼 표시 여부
            dictRemoveFile: '삭제', // 삭제버튼 표시 텍스트
            uploadMultiple: true, // 다중업로드 기능
            accept: function(file, done) {
                console.log("accept()2")
                console.log("multiFileUpload accept!!");
                if (file.name == "justinbieber.jpg") {
                    done("Naha, you don't.");
                } else {
                    done();
                }
            },
            success: function (file, response) {
                console.log("success");
                console.log(file);
                console.log(response);
            },
            error: function (file, response) {
                console.log("error");
                console.log(response);
            },
            addedfile: function (file) {
                console.log("addedfile");
                console.log(file);
                uploadFiles.push(file);

                makePreviewList(file, 0);
            }
        };
        Dropzone.options.fileTypeValidation = {
            paramName: "upfiles",
            maxFiles: 10,
            maxFilesize: 10, 
            acceptedFiles: "image/*,application/pdf,.psd",
            autoProcessQueue: false, // 자동업로드 여부 (true일 경우, 바로 업로드 되어지며, false일 경우, 서버에는 올라가지 않은 상태임 processQueue() 호출시 올라간다.)
            clickable: true, // 클릭가능여부
            thumbnailHeight: 90, // Upload icon size
            thumbnailWidth: 90, // Upload icon size
            parallelUploads: 99, // 동시파일업로드 수(이걸 지정한 수 만큼 여러파일을 한번에 컨트롤러에 넘긴다.)
            addRemoveLinks: true, // 삭제버튼 표시 여부
            dictRemoveFile: '삭제', // 삭제버튼 표시 텍스트
            uploadMultiple: true, // 다중업로드 기능
            accept: function(file, done) {
                console.log("accept()3")
                if (file.name == "justinbieber.jpg") {
                    done("Naha, you don't.");
                } else {
                    done();
                }
            }
        };
    }
    return {
        init: function() {
            console.log("dropzone init()")
            DropzoneUpload();
        }
    };
}();
DropzoneFileUpload.init();