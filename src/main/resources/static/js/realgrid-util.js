/**
 * RealGrid 공통 기능
 */


// 그리드 생성
function createMainGrid(container, provider, grid, fields, colums) {
	  provider = new RealGrid.LocalDataProvider();
	  grid = new RealGrid.GridView(container);

	  grid.setDataSource(provider);
	  provider.setFields(fields);
	  grid.setColumns(colums);

	  grid.displayOptions.emptyMessage = "표시할 데이타가 없습니다.";
	  grid.displayOptions.rowHeight = 36;
	  grid.header.height = 40;
	  grid.footer.height = 40;
	  grid.stateBar.width = 30;
	  grid.editOptions.insertable = true;
	  grid.editOptions.appendable = true;
	  grid.footers.visible = false;//하단 소계 감추기
	  //delete 기능 추가
	  grid.setEditOptions({deletable: true});
      //삭제버튼 클릭시 그리드에 삭제하지 않고 플레그만 바꾼다.
	  provider.setOptions({softDeleting: true});
	  provider.setOptions({deleteCreated: true});
	  
	  //크기자동변경
	  grid.setDisplayOptions({fitStyle: "even"});
	  
	  //크기자동변경
	  //grid.setDisplayOptions({fitStyle: "even"});
	  //드롭다운 뒤로 갈 때
	  //grid.setEditorOptions({   viewGridInside: true })
	  //체크버튼 비활성화		  
	  grid.setRowIndicator({ visible: true  });
	  grid.setCheckBar({	 visible: false  });
	  grid.setStateBar({	 visible: true  });  
}