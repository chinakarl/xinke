package com.xinke.common.page;

/**
 * @ClassName: PageUtils
 * @Description: 分页帮助工具类
 * @author sunjl
 */
public abstract class PageUtils {

    /**
     * @Title: getFirstResult
     * @Description: 获取某页的第一个对象位置
     * @param pageNumber 页数
     * @param pageSize 每页大小
     * @return 在全部记录中的位置
     */
    public static int getFirstResult(final int pageNumber, final int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("[pageSize] must great than zero");
        }
        return (pageNumber - 1) * pageSize;
    }

    /**
     * @Title: generateLinkPageNumbers
     * @Description: 生成分页链接
     * @param currentPageNumber 当前页数
     * @param lastPageNumber 最后一个页的页数
     * @param count 分页链接显示多少个链接
     * @return 显示的分页链接
     */
    public static Integer[] generateLinkPageNumbers(final int currentPageNumber, final int lastPageNumber, final int count) {
        final int avg = count / 2;

        int startPageNumber = currentPageNumber - avg;
        if (startPageNumber <= 0) {
            startPageNumber = 1;
        }

        int endPageNumber = startPageNumber + count - 1;
        if (endPageNumber > lastPageNumber) {
            endPageNumber = lastPageNumber;
        }

        if (endPageNumber - startPageNumber < count) {
            startPageNumber = endPageNumber - count;
            if (startPageNumber <= 0) {
                startPageNumber = 1;
            }
        }

        final java.util.List<Integer> result = new java.util.ArrayList<Integer>();
        for (int i = startPageNumber; i <= endPageNumber; i = i + 1) {
            result.add(new Integer(i));
        }
        return result.toArray(new Integer[result.size()]);
    }

    /**
     * @Title: computeLastPageNumber
     * @Description: 计算最后一个页的页数
     * @param totalElements 元素总数
     * @param pageSize 每页大小
     * @return 一共多少页
     */
    public static int computeLastPageNumber(final int totalElements, final int pageSize) {
        int result = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
        if (result <= 1) {
            result = 1;
        }
        return result;
    }

    /**
     * @Title: computePageNumber
     * @Description: 计算应该显示的页数
     * @param pageNumber 想要显示的页数
     * @param pageSize 每页大小
     * @param totalElements 元素总数
     * @return 应该显示第几页
     */
    public static int computePageNumber(final int pageNumber, final int pageSize, final int totalElements) {
        if (pageNumber <= 1) {
            return 1;
        }
        if (Integer.MAX_VALUE == pageNumber || pageNumber > computeLastPageNumber(totalElements, pageSize)) { // last page
            return computeLastPageNumber(totalElements, pageSize);
        }
        return pageNumber;
    }
}
