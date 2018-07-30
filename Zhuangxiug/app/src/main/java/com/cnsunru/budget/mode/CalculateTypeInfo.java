package com.cnsunru.budget.mode;

import java.util.List;

/**
 * 房屋类型数据
 */

public class CalculateTypeInfo {
        /**
         * id : 5
         * title : 卧室
         * choose_num : [{"value":"1","title":"1室","status":1},{"value":"2","title":"2室","status":1},{"value":"3","title":"3室","status":1},{"value":"4","title":"4室","status":1}]
         */

        private String id;
        private String title;
        private List<ChooseNumBean> choose_num;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ChooseNumBean> getChoose_num() {
            return choose_num;
        }

        public void setChoose_num(List<ChooseNumBean> choose_num) {
            this.choose_num = choose_num;
        }

        public static class ChooseNumBean {
            /**
             * value : 1
             * title : 1室
             * status : 1
             */

            private String value;
            private String title;
            private int status;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
}
