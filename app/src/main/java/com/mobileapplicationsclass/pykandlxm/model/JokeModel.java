package com.mobileapplicationsclass.pykandlxm.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22 0022.
 */
public class JokeModel {
    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"历史课上 老师:''五四运动是那天爆发的？'' 小明:''五月四日'' 老师:''............'' 老师:''五四运动的主力是什么？'' 小明:''学生及工人'' 老师:''恩。很好。那五四运动爆发的原因是什么'' 小明:''五一只放三天假'' 师 猝。","hashId":"be2ebe18c7542c75c60ef5837868c9a8","unixtime":1474536830,"updatetime":"2016-09-22 17:33:50"},{"content":"刚才送水的路上，迎面一个美女边走边玩手机，一不小心踩空跪倒在地上，我刚准备要扶她，她痛苦的说：\u201c你先别扶我，帮我把手机捡起来给我这个姿势拍个照我发朋友圈，我男朋友出差了我膝盖破了我怕他误会\u201d！！","hashId":"18e24b8e5375b1b9a71d2d3146f0befa","unixtime":1474536830,"updatetime":"2016-09-22 17:33:50"},{"content":"下午肚子饿，看见同事桌上有瓶酸奶，想都没想就喝了，一会同事来了大叫到：\u201c我的洗面奶怎么不见了，108块啊！\u201d哥没说话，只是默默的走向洗手间，好不容易吐的差不多了，眼泪叭嚓滴回到座位上时，同事抱着一个瓶子说：\u201c吓死我了，洗面奶滚到桌子下了，我的酸奶怎么又不见了呢。\u201d哥心里直骂：喝你点酸奶，把人往死里整。","hashId":"3724bff1224c07e127e3993e45965541","unixtime":1474536230,"updatetime":"2016-09-22 17:23:50"},{"content":"半夜时分，男子正在床上忙得满头大汗！突然，男子接到邻居电话:\u201c你们有完没完，别让你老婆再叫了！全小区都听见了！\u201d男子连忙停下来！给邻居道歉:对不起啊！我在东莞出差呢，明天我回去说说她！","hashId":"036fd0b14354d175025710100aca3b3a","unixtime":1474536230,"updatetime":"2016-09-22 17:23:50"},{"content":"记得上初中，中午午休的时候，我和哥们躲在厕所里抽烟。  听见外面有人来，哥们猛吸一口，把烟甩了。  进来的是教导主任，他看见我们靠着窗户，便问：你们在干嘛呢？  我慌了，转头看向哥们。他的表情我至今难忘。只见他鼻孔里冒着好多白烟，然后说到：我在生气。","hashId":"b532f569836e1acf4d2f1bb6766b5ce0","unixtime":1474535630,"updatetime":"2016-09-22 17:13:50"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * content : 历史课上 老师:''五四运动是那天爆发的？'' 小明:''五月四日'' 老师:''............'' 老师:''五四运动的主力是什么？'' 小明:''学生及工人'' 老师:''恩。很好。那五四运动爆发的原因是什么'' 小明:''五一只放三天假'' 师 猝。
         * hashId : be2ebe18c7542c75c60ef5837868c9a8
         * unixtime : 1474536830
         * updatetime : 2016-09-22 17:33:50
         */

        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
