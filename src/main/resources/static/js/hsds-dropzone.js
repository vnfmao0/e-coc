var hsdsDropzone = {
    set : function( formId, btnId ) {

        //dropzone 세팅
        var myDropzone = new Dropzone('#' + formId, {
            url : "/cmmfile/singleFileUpload",
            autoProcessQueue: false,            //자동 업로드
            paramName: "file",                  //전송받는 파일 파라미터명
            addRemoveLinks: true,
            //acceptedFiles: "application/pdf",   //파일 종류
            uploadMultiple: true,               //다중 파일 업로드
            parallelUploads: 10,                //동시 업로드 파일 개수
            maxFilesize: 10,                    //최대 파일 사이즈 (MB)
            maxFiles: 10,                       //첨부 개수
            // params: {
            //     "EMAIL" : "yunsd@careercare.co.kr",//global.userInfo.EMAIL,
            //     "FILETYPE" : "pdf"
            // },
            init : function(){

                var myDropzone = this;

                /*파일 전송*/
                $("#"+btnId).on('click', function () {
                    console.log(btnId + " clicked!!");
                    myDropzone.processQueue();
                });

                /*최대 허용 파일 개수보다 많이 올린 경우 제어*/
                myDropzone.on("addedfile", function(event) {
                    while (this.files.length > this.options.maxFiles) {
                        this.removeFile(this.files[0]);
                    }
                });

                /*팡리 전송이 완료되면 목록에있는 파일 제거*/
                myDropzone.on("complete", function(file) {
                    myDropzone.removeFile(file);
                });
            }
        });
    }
}