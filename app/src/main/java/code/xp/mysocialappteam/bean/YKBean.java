package code.xp.mysocialappteam.bean;

/**
 * Created by dell on 2017/12/15.
 */

public class YKBean {
    /**
     * code : 200
     * msg : success
     * data : {"surfer_id":151}
     */
    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * surfer_id : 151
         */

        private int surfer_id;

        public int getSurfer_id() {
            return surfer_id;
        }

        public void setSurfer_id(int surfer_id) {
            this.surfer_id = surfer_id;
        }
    }
}
