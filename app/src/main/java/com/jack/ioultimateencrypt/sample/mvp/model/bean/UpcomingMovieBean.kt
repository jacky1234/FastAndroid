package com.jack.ioultimateencrypt.sample.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * 2017/8/25.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 */

class UpcomingMovieBean {

    var attention: List<AttentionBean>? = null
    var moviecomings: List<MoviecomingsBean>? = null

    class AttentionBean {
        /**
         * actor1 : 芬恩·怀特海德
         * actor2 : 汤姆·格林-卡尼
         * director : 克里斯托弗·诺兰
         * id : 230741
         * image : http://img5.mtime.cn/mt/2017/07/13/103549.65637900_1280X720X2.jpg
         * isFilter : false
         * isTicket : true
         * isVideo : true
         * locationName : 美国
         * rDay : 1
         * rMonth : 9
         * rYear : 2017
         * releaseDate : 9月1日上映
         * title : 敦刻尔克
         * type : 剧情 / 战争
         * videoCount : 3
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/07/26/102538.70743886.jpg","length":140,"title":"敦刻尔克 中文版终极版预告","url":"http://vfx.mtime.cn/Video/2017/07/26/mp4/170726102433803464.mp4","videoId":66798},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/07/12/132353.90410786.jpg","length":30,"title":"敦刻尔克 中文定档预告","url":"http://vfx.mtime.cn/Video/2017/07/12/mp4/170712132402662967.mp4","videoId":66563},{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/08/22/164207.63386244.jpg","length":200,"title":"敦刻尔克 \u201c绝不投降\u201c预告片","url":"http://vfx.mtime.cn/Video/2017/07/05/mp4/170705084043788253.mp4","videoId":66453}]
         * wantedCount : 11095
         */

        var actor1: String? = null
        var actor2: String? = null
        var director: String? = null
        var id: Int = 0
        var image: String? = null
        var isIsFilter: Boolean = false
        var isIsTicket: Boolean = false
        var isIsVideo: Boolean = false
        var locationName: String? = null
        var rDay: Int = 0
        var rMonth: Int = 0
        var rYear: Int = 0
        var releaseDate: String? = null
        var title: String? = null
        var type: String? = null
        var videoCount: Int = 0
        var wantedCount: Int = 0
        var videos: List<VideosBean>? = null

        class VideosBean {
            /**
             * hightUrl :
             * image : http://img5.mtime.cn/mg/2017/07/26/102538.70743886.jpg
             * length : 140
             * title : 敦刻尔克 中文版终极版预告
             * url : http://vfx.mtime.cn/Video/2017/07/26/mp4/170726102433803464.mp4
             * videoId : 66798
             */

            var hightUrl: String? = null
            var image: String? = null
            var length: Int = 0
            var title: String? = null
            var url: String? = null
            var videoId: Int = 0
        }
    }


    class MoviecomingsBean(isHeader: Boolean, header: String?) : SectionEntity<String>(isHeader, header) {
        /**
         * actor1 : 张俪
         * actor2 : 锦荣
         * director : 马伟豪
         * id : 228075
         * image : http://img5.mtime.cn/mt/2017/04/24/102441.44157032_1280X720X2.jpg
         * isFilter : false
         * isTicket : false
         * isVideo : true
         * locationName : 中国
         * rDay : 28
         * rMonth : 8
         * rYear : 2017
         * releaseDate : 8月28日上映
         * title : 蝴蝶公墓
         * type : 奇幻 / 爱情
         * videoCount : 1
         * videos : [{"hightUrl":"","image":"http://img5.mtime.cn/mg/2017/04/23/153309.63189441.jpg","length":30,"title":"蝴蝶公墓 定档预告片","url":"http://vfx.mtime.cn/Video/2017/04/23/mp4/170423153307480211.mp4","videoId":65401}]
         * wantedCount : 339
         */

        var actor1: String? = null
        var actor2: String? = null
        var director: String? = null
        var id: Int = 0
        var image: String? = null
        var isIsFilter: Boolean = false
        var isIsTicket: Boolean = false
        var isIsVideo: Boolean = false
        var locationName: String? = null
        var rDay: Int = 0
        var rMonth: Int = 0
        var rYear: Int = 0
        var releaseDate: String? = null
        var title: String? = null
        var type: String? = null
        var videoCount: Int = 0
        var wantedCount: Int = 0
        var videos: List<VideosBeanX>? = null

        class VideosBeanX {
            /**
             * hightUrl :
             * image : http://img5.mtime.cn/mg/2017/04/23/153309.63189441.jpg
             * length : 30
             * title : 蝴蝶公墓 定档预告片
             * url : http://vfx.mtime.cn/Video/2017/04/23/mp4/170423153307480211.mp4
             * videoId : 65401
             */

            var hightUrl: String? = null
            var image: String? = null
            var length: Int = 0
            var title: String? = null
            var url: String? = null
            var videoId: Int = 0
        }
    }
}
