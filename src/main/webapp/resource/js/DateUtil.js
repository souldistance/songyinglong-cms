if (!DateUtil) {
    var DateUtil = {
        //日期格式部分
        yyyy_MM_dd_HH_mm_ss: "yyyy-MM-dd HH:mm:ss",
        yyyy_MM_dd: "yyyy-MM-dd",
        yyyy_MM_dd_1: "yyyy/MM/dd",
        yyyyMMdd: "yyyyMMdd",
        HH_mm_ss: "HH:mm:ss",

        init: function () {

        },
        /**
         * 判断数据是否为空
         * @param data
         * @returns {boolean}
         */
        isNull: function (data) {
            if (data == null || data == undefined || data == '') {
                return true;
            } else {
                return false;
            }
        },
        /**
         * 判断是否为日期
         * @param date 不支持yyyyMMdd格式
         * @returns {boolean}
         */
        isDate: function(date){
            if (isNaN(date) && !isNaN(Date.parse(date))) {
                return true;
            } else {
                return false;
            }
        },
        /**
         * 获取当前日期
         * @returns {Date}
         */
        getNowDate: function () {
            return new Date();
        },
        /**
         * 获取当前时间戳
         * @returns {number}
         */
        getNowTimeStamp: function () {
            return parseInt(Math.ceil(new Date().getTime()));
        },
        /**
         * Date日期格式化
         * @param date
         * @param pattern 可为空，默认yyyy-MM-dd HH:mm:ss
         * @returns {string}
         */
        format: function (date, pattern) {
            var yy = date.getFullYear();      //年
            var mm = date.getMonth() + 1;     //月
            var dd = date.getDate();          //日
            var hh = date.getHours();         //时
            var ii = date.getMinutes();       //分
            var ss = date.getSeconds();       //秒

            var clock = yy + "-";
            if (mm < 10) clock += "0";
            clock += mm + "-";
            if (dd < 10) clock += "0";
            clock += dd + " ";
            if (hh < 10) clock += "0";
            clock += hh + ":";
            if (ii < 10) clock += '0';
            clock += ii + ":";
            if (ss < 10) clock += '0';
            clock += ss;
            if (DateUtil.isNull(pattern) || pattern == DateUtil.yyyy_MM_dd_HH_mm_ss) {
                return clock;
            } else if (pattern == DateUtil.yyyy_MM_dd) {
                return clock.substring(0, 10);
            } else if (pattern == DateUtil.HH_mm_ss) {
                return clock.substring(11);
            } else if (pattern == DateUtil.yyyy_MM_dd_1) {
                return clock.substring(0, 10).replace(/-/g, '/');
            } else if (pattern == DateUtil.yyyyMMdd) {
                return clock.substring(0, 10).replace(/-/g, '');
            } else {
                return clock;
            }
        },
        /**
         * 日期字符串转时间戳
         * @param date  不支持yyyyMMdd格式
         * @returns {number}
         */
        strDate2TimeStamp: function (date) {
            date = date.replace(/-/g, '/');
            return new Date(date).getTime();
        },
        /**
         * 时间戳转日期字符串(yyyy-MM-dd HH:mm:ss)
         * @param timeStamp
         * @returns {string}
         */
        timeStamp2strDate: function (timeStamp) {
            var d = new Date(timeStamp);    //根据时间戳生成的时间对象
            var date = (d.getFullYear()) + "-"
                + (d.getMonth() + 1 < 10?"0"+(d.getMonth()+1):d.getMonth()+1) + "-"
                + (d.getDate()<10?"0"+d.getDate():d.getDate()) + " "
                + (d.getHours()<10?"0"+d.getHours():d.getHours()) + ":"
                + (d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes()) + ":"
                + (d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds());
            return date;
        },
        /**
         * 获取几天前日期(1代表明天，-1 代表前一天，-2前两天...)
         * @param num
         * @param separator 连接符 如果为-,则结果为:yyyy-MM-dd
         */
        getDay:function (num,separator) {
            var today = new Date();
            var nowTime = today.getTime();
            var ms = 24*3600*1000*num;
            today.setTime(parseInt(nowTime + ms));
            var oYear = today.getFullYear();
            var oMoth = (today.getMonth() + 1).toString();
            if (oMoth.length <= 1) oMoth = '0' + oMoth;
            var oDay = today.getDate().toString();
            if (oDay.length <= 1) oDay = '0' + oDay;
            return oYear + separator + oMoth + separator + oDay;
        },
        /**
         * 判断某一年是否是闰年
         * @param year
         * @returns {boolean}
         */
        isLeapYear:function (year) {
            return((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0);
        },
        /**
         * 获取某年某个月的天数(西方月份)
         * @param year
         * @param month 从0开始
         */
        getDaysOfMonthEN:function (year,month) {
            return [31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
        },
        /**
         * 获取某年某个月的天数(中国月份)
         * @param year
         * @param month 从1开始
         */
        getDaysOfMonthCN:function (year,month) {
            return ['中国没有0月',31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
        },
        /**
         * 计算一个日期是当年的第几天
         * @param date 字符串日期
         * @returns {number}
         */
        dayOfTheYear:function (date) {
            var obj = new Date(date);
            var year = obj.getFullYear();
            var month = obj.getMonth(); //从0开始
            var days = obj.getDate();
            var daysArr = [31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            for(var i = 0; i < month; i++) {
                days += daysArr[i];
            }
            return days;
        },
        /**
         * 比较两个时间大小(不支持yyyyMMdd格式)
         *    date1>date2 return 1
         *    date1<date2 return -1
         *    date1==date2 return 0
         * @returns {number}
         */
        compareTime:function (date1, date2) {
            if(Date.parse(date1.replace(/-/g, "/")) > Date.parse(date2.replace(/-/g, "/"))) {
                return 1;
            } else if(Date.parse(date1.replace(/-/g, "/")) < Date.parse(date2.replace(/-/g, "/"))) {
                return -1;
            } else if(Date.parse(date1.replace(/-/g, "/")) == Date.parse(date2.replace(/-/g, "/"))) {
                return 0;
            }
        },
        /**
         * 获取本周开始日期
         * @returns {*|string}
         */
        getWeekStartDay:function () {
            var now = new Date();
            var date = new Date(now.getFullYear(), now.getMonth(), now.getDate() - now.getDay() + 1);
            return DateUtil.format(date,DateUtil.yyyy_MM_dd);
        },
        /**
         * 获取本周结束日期
         * @returns {*|string}
         */
        getWeekEndDay:function () {
            var now = new Date();
            var date = new Date(now.getFullYear(), now.getMonth(), now.getDate() + (7 - now.getDay()));
            return DateUtil.format(date,DateUtil.yyyy_MM_dd);
        },
        /**
         * 获取上周开始日期
         * @returns {*|string}
         */
        getUpWeekStartDay:function () {
            var now = new Date();
            var date = new Date(now.getFullYear(), now.getMonth(), now.getDate() - now.getDay() -6);
            return DateUtil.format(date,DateUtil.yyyy_MM_dd);
        },
        /**
         * 获取上周结束日期
         * @returns {*|string}
         */
        getUpWeekEndDay:function () {
            var now = new Date();
            var date = new Date(now.getFullYear(), now.getMonth(), now.getDate() - now.getDay());
            return DateUtil.format(date,DateUtil.yyyy_MM_dd);
        },

    }
}
