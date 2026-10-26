package Simple.Cruid.Simple.Oauth2.Crud.dtos.response;

import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

public class PageMeta {

    private boolean hasNext;
    private boolean haspPrevPage;
    private int currentPage;
    private long totalItemsCount;
    private int currentItemsCount;
    private int pageSize;
    private long offset;
    private int pageCount;
    private int nextPageNumber;
    private int prevPageNumber;
    private String nextPageUrl;
    private String prevPageUrl;

    public PageMeta(){}

    public boolean isHasNext() {
        return hasNext;
    }

    public static PageMeta build(Page resourcePage, String basePath){
        PageMeta pageMeta = new PageMeta();
        PageMeta pageable = (PageMeta) resourcePage.getPageable();

        pageMeta.setTotalItemsCount(resourcePage.getTotalElements());
        pageMeta.setOffset(pageMeta.offset);
        pageMeta.setPageSize(pageable.getPageSize());
        pageMeta.setCurrentItemsCount(resourcePage.getContent().size());
        pageMeta.setPageSize(resourcePage.getTotalPages());

        pageMeta.setCurrentPage(resourcePage.getNumber()+1);
        pageMeta.setHasNextPage(resourcePage.hasNext());
        pageMeta.setHaspPrevPage(resourcePage.hasPrevious());

        if(resourcePage.hasNext()){
            pageMeta.setNextPageNumber(resourcePage.getPageable().next().getPageNumber()+1);
            pageMeta.setNextPageUrl(String.format("%s?page_size=%d",basePath,pageMeta.getPageSize(),pageMeta.getNextPageNumber()));
        }
        else {
            pageMeta.setNextPageNumber(pageMeta.getPageCount());
            pageMeta.setNextPageUrl(String.format("%s?page_size=%d&page=%d",
                    basePath,pageMeta.getPageSize(),pageMeta.getNextPageNumber()));
        }

        if(resourcePage.hasPrevious()){
             pageMeta.setPrevPageNumber(resourcePage.getPageable().previousOrFirst().getPageNumber()+1);
             pageMeta.setPrevPageUrl(String.format("%s?page_size=%d&page=%d",basePath,pageMeta.getPageSize(),
                     pageMeta.getPrevPageNumber()));
        }
        else {
            pageMeta.setPrevPageNumber(1);
            pageMeta.setPrevPageUrl(String.format("%s?page_size=%d&page=%d",basePath,
                    pageMeta.getPageSize(),pageMeta.getPrevPageNumber()));
        }
    return pageMeta;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHaspPrevPage() {
        return haspPrevPage;
    }

    public void setHaspPrevPage(boolean haspPrevPage) {
        this.haspPrevPage = haspPrevPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    private void setHasNextPage(boolean hasNext) {
        this.hasNext = hasNext;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItemsCount() {
        return totalItemsCount;
    }
    public void setHasPrevPage(boolean hasPrevPage) {
        this.haspPrevPage = hasPrevPage;
    }
    public void setTotalItemsCount(long totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }

    public int getPrevPageNumber() {
        return prevPageNumber;
    }

    public void setPrevPageNumber(int prevPageNumber) {
        this.prevPageNumber = prevPageNumber;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public int getCurrentItemsCount() {
        return currentItemsCount;
    }

    public void setCurrentItemsCount(int currentItemsCount) {
        this.currentItemsCount = currentItemsCount;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public String getPrevPageUrl() {
        return prevPageUrl;
    }

    public void setPrevPageUrl(String prevPageUrl) {
        this.prevPageUrl = prevPageUrl;
    }
}
