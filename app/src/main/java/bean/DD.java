package bean;

import java.util.List;

/**
 * Created by asus on 2017/10/22.
 */

public class DD {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-10-22T14:29:30","orderid":844,"price":99,"status":0,"uid":137},{"createtime":"2017-10-22T14:56:27","orderid":881,"price":111.99,"status":0,"uid":137}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2017-10-22T14:29:30
         * orderid : 844
         * price : 99.0
         * status : 0
         * uid : 137
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
