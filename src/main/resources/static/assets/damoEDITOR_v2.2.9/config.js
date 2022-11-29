damoConfig = {
    uploadURL: "/editor/damoEditorUpload.dmn",
    filesize: 20 * 1000 * 1000,        // 20M
    useEditMode: true,
    language: 'ko',
    fonts: ["굴림", "나눔바른고딕", "돋움", "돋움체"],
	setFocus: true,
    xss: {
        used: true,
        removeTag: "script,iframe,object,applet,embed,form",
        removeEvent: "all"
    }
}    
