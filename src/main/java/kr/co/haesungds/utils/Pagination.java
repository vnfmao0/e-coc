package kr.co.haesungds.utils;

public class Pagination {
    private int listSize = 12;		// 한페이지당 표시할 게시물 갯수
    private int rangeSize = 10;      // 하단 페이지번호 부분 표시 갯수
    private int page;           // 현제 페이지 번호
    private int range;          // 하단 페이지번호의 인덱스 번호 -
    private int listCnt;        // 총 개시물 수
    private int pageCnt;        // 총 페이지 수
    private int startPage;      // 시작 페이지 번호
    private int endPage;        // 끝 페이지 번호
    private int startList;      // 현재 페이지의 첫번째 게시물 번호
    private int endList;      	// 현재 페이지의 마지막 게시물 번호
    private boolean prev;       // 이전버튼 표시여부
    private boolean next;       // 다음버튼 표시여부

    public int getListSize() {
        return listSize;
    }
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }
    public int getRangeSize() {
        return rangeSize;
    }
    public void setRangeSize(int rangeSize) {
        this.rangeSize = rangeSize;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getListCnt() {
        return listCnt;
    }
    public void setListCnt(int listCnt) {
        this.listCnt = listCnt;
    }
    public int getPageCnt() {
        return pageCnt;
    }
    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public int getStartList() {
        return startList;
    }
    public void setStartList(int startList) {
        this.startList = startList;
    }
    public int getEndList() {
        return endList;
    }
    public void setEndList(int endList) {
        this.endList = endList;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }

    public void pageInfo(int page, int listCnt) {
        this.page = page;
        this.listCnt = listCnt;

        //전체 페이지 수
        this.pageCnt = (int) Math.ceil((float) this.listCnt / this.listSize);
        this.range = (int) Math.ceil((float) this.page / this.rangeSize);

        //시작 페이지 번호
        this.startPage = (this.range - 1) * this.rangeSize + 1;

        //끝 페이지 번호
        this.endPage = (this.range * this.rangeSize) < this.pageCnt ? (this.range * this.rangeSize) : this.startPage + (this.pageCnt - this.startPage);
        if(this.endPage == 0) this.endPage = 1;

        //게시물 시작번호
        this.startList = (this.page - 1) * this.listSize + 1;	//쿼리 row_number() 는 1부터 시작이라... +1 함.

        //게시물 시작번호
        this.endList = this.startList + this.listSize - 1;		//query between A and B 로 조회할거라... -1 함.

        //이전 버튼 표시여부
        this.prev = this.range == 1 ? false : true;

        //다음 버튼 표시여부
        this.next = this.endPage >= this.pageCnt ? false : true;

    }

    @Override
    public String toString() {
        return "Pagination [listSize=" + listSize + ", rangeSize=" + rangeSize + ", page=" + page + ", range=" + range
                + ", listCnt=" + listCnt + ", pageCnt=" + pageCnt + ", startPage=" + startPage + ", endPage=" + endPage
                + ", startList=" + startList + ", endList=" + endList + ", prev=" + prev + ", next=" + next + "]";
    }
}
