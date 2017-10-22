package bean;

/**
 * Created by asus on 2017/10/17.
 */

public class GSON {

    /**
     * msg :
     * seller : {"description":"我是商家2","icon":"http://120.27.23.105/images/icon.png","name":"商家2","productNums":999,"score":5,"sellerid":2}
     * code : 0
     * data : {"bargainPrice":399,"createtime":"2017-10-02T15:20:02","detailUrl":"https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg","itemtype":0,"pid":81,"price":699,"pscid":85,"salenum":0,"sellerid":2,"subhead":"满2件，总价打6.50折","title":"Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)"}
     */

    private String msg;
    private SellerBean seller;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class SellerBean {
        /**
         * description : 我是商家2
         * icon : http://120.27.23.105/images/icon.png
         * name : 商家2
         * productNums : 999
         * score : 5.0
         * sellerid : 2
         */

        private String description;
        private String icon;
        private String name;
        private int productNums;
        private double score;
        private int sellerid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProductNums() {
            return productNums;
        }

        public void setProductNums(int productNums) {
            this.productNums = productNums;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }
    }

    public static class DataBean {
        /**
         * bargainPrice : 399.0
         * createtime : 2017-10-02T15:20:02
         * detailUrl : https://item.m.jd.com/product/1439822107.html?utm_source=androidapp&utm_medium=appshare&utm_campaign=t_335139774&utm_term=QQfriends
         * images : https://m.360buyimg.com/n0/jfs/t5887/201/859509257/69994/6bde9bf6/59224c24Ne854e14c.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5641/233/853609022/57374/5c73d281/59224c24N3324d5f4.jpg!q70.jpg
         * itemtype : 0
         * pid : 81
         * price : 699.0
         * pscid : 85
         * salenum : 0
         * sellerid : 2
         * subhead : 满2件，总价打6.50折
         * title : Gap男装 休闲舒适简约水洗五袋直筒长裤紧身牛仔裤941825 深灰色 33/32(175/84A)
         */

        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private int itemtype;
        private int pid;
        private double price;
        private int pscid;
        private int salenum;
        private int sellerid;
        private String subhead;
        private String title;

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getItemtype() {
            return itemtype;
        }

        public void setItemtype(int itemtype) {
            this.itemtype = itemtype;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
