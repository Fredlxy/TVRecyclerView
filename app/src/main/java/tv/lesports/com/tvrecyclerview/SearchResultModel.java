package tv.lesports.com.tvrecyclerview;


/**
 * Created by liuyu8 on 2015/10/16.
 */
public class SearchResultModel implements java.io.Serializable{


    /**
     * id : 23879869
     * createTime : 20151029125453
     * duration : 348
     * desc : 足坛从此再无“邵氏弯刀”
     * imageUrl : {"400*225":"http://i0.letvimg.com/lc02_yunzhuanma/201510/30/11/34/ea1a4d0d5d77c6e27935f848aba28d6b_36552812/thumb/2_400_225.jpg","400*300":"http://i0.letvimg.com/lc02_yunzhuanma/201510/30/11/34/ea1a4d0d5d77c6e27935f848aba28d6b_36552812/thumb/2_400_300.jpg","960*540":""}
     * name : 足坛从此再无“邵氏弯刀”
     * type : 4
     * vid : 23879869
     * commentId : 23879869
     */
    public int id;

    public String name;

    public SearchResultModel(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
