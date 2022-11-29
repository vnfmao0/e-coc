function toastrAlert(msg, type, option) {
    toastr.options.escapeHtml = true;
    toastr.options.closeButton = true;
    toastr.options.newestOnTop = false;
    toastr.options.progressBar = false;
    toastr.options.positionClass = "toast-bottom-right";

    if (type == "Success") {
        toastr.success(msg, type, option);
    }
}

function toastrSuccess(msg) {
    toastr.options.escapeHtml = true;
    toastr.options.closeButton = true;
    toastr.options.newestOnTop = false;
    toastr.options.progressBar = false;
    toastr.options.positionClass = "toast-bottom-right";
    toastr.success(msg != null || msg != '' ? msg : '정상적으로 처리되었습니다.', 'Success', {timeOut: 1500});
}

function toastrError(msg) {
    toastr.options.escapeHtml = true;
    toastr.options.closeButton = true;
    toastr.options.newestOnTop = false;
    toastr.options.progressBar = false;
    toastr.options.positionClass = "toast-bottom-right";
    toastr.error(msg != null || msg != '' ? msg : '처리중 오류가 발생했습니다.', 'Error', {timeOut: 1500});
}

function toastrWarning(msg) {
    toastr.options.escapeHtml = true;
    toastr.options.closeButton = true;
    toastr.options.newestOnTop = false;
    toastr.options.progressBar = false;
    toastr.options.positionClass = "toast-bottom-right";
    toastr.warning(msg != null || msg != '' ? msg : '처리중 오류가 발생했습니다.', 'Warning', {timeOut: 1500});
}
function toastrInfo(msg) {
    toastr.options.escapeHtml = true;
    toastr.options.closeButton = true;
    toastr.options.newestOnTop = false;
    toastr.options.progressBar = false;
    toastr.options.positionClass = "toast-bottom-right";
    toastr.info(msg != null || msg != '' ? msg : '처리중 오류가 발생했습니다.', 'Info', {timeOut: 1500});
}
