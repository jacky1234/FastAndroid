package com.jack.ioultimateencrypt.sample.mvp.model.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * 2017/9/23.
 * github:[https://github.com/jacky1234]
 * @author  jackyang
 *
 * movie detail
 */
class MovieDetailBean {

    class MovieDetailType(mItemType: Int) : MultiItemEntity {
        val mItemType = mItemType

        override fun getItemType(): Int {
            return mItemType
        }

        companion object {
            @JvmStatic
            val TYPE_STORY = 1          //剧情

            @JvmStatic
            val TYPE_STAFF = 2          //导演，演员

            @JvmStatic
            val TYPE_RANK = 3           //排名
        }
    }

    /**
     * image : http://img5.mtime.cn/mt/2017/09/14/164036.37791477_1280X720X2.jpg
     * titleCn : 猩球崛起3：终极之战
     * titleEn : War for the Planet of the Apes
     * rating : 7.7
     * scoreCount : 3133
     * year : 2017
     * content : 凯撒率领的猿族，被迫与上校领导的人类军队展开生死之战。在影片中猿族将面临前所未有的重创，由此激发了凯撒内心中黑暗的一面，心中燃起复仇的烈火。最终，凯撒与人类首领上校面对面进行了一场关乎猿族和人类命运的终极之战。
     * type : ["动作","冒险","科幻","剧情","惊悚"]
     * runTime : 140分钟
     * url : http://movie.mtime.com/218546/
     * wapUrl : http://movie.wap.mtime.com/218546/
     * isEggHunt : false
     * commonSpecial : 猿族人类生死激战一触即发
     * hotRanking : -1
     * weekBoxOffice :
     * totalBoxOffice :
     * weekBoxOfficeUnit :
     * totalBoxOfficeUnit :
     * director : {"directorId":902708,"directorName":"马特·里夫斯","directorNameEn":"Matt Reeves","directorImg":"http://img21.mtime.cn/ph/2010/05/11/103014.33218315_1280X720X2.jpg"}
     * actorList : [{"actorId":915215,"actor":"安迪·瑟金斯","actorEn":"Andy Serkis","actorImg":"http://img31.mtime.cn/ph/2016/09/08/174121.83061563_1280X720X2.jpg","roleName":"凯撒","roleImg":"http://img5.mtime.cn/mg/2017/07/17/145517.74820200_80X80X4.jpg"},{"actorId":913924,"actor":"伍迪·哈里森","actorEn":"Woody Harrelson","actorImg":"http://img31.mtime.cn/ph/2016/08/29/142212.56972747_1280X720X2.jpg","roleName":"上校","roleImg":"http://img5.mtime.cn/mg/2017/07/17/145526.31534299_80X80X4.jpg"}]
     * isTicket : true
     * showCinemaCount : 190
     * showtimeCount : 1299
     * showDay : 1506153600
     * style : {"ifLeadPage":0,"leadUrl":"","leadImag":"http://img2.mtime.cn/mg/.jpg"}
     * is3D : true
     * isIMAX : false
     * isIMAX3D : true
     * isDMAX : true
     * festivals : []
     * awards : []
     * totalWinAward : 0
     * totalNominateAward : 0
     * directors : ["马特·里夫斯"]
     * actors : ["安迪·瑟金斯","伍迪·哈里森"]
     * release : {"location":"中国","date":"2017-9-15"}
     * imageCount : 94
     * images : ["http://img5.mtime.cn/pi/2017/03/31/145310.46589401_1280X720X2.jpg","http://img5.mtime.cn/pi/2017/02/04/171726.73117171_1280X720X2.jpg","http://img5.mtime.cn/pi/2017/03/31/145307.87130515_1280X720X2.jpg","http://img5.mtime.cn/pi/2017/03/31/145303.65543642_1280X720X2.jpg"]
     * video : http://vfx.mtime.cn/Video/2017/09/11/mp4/170911103724173128.mp4
     * videoId : 67603
     * videoCount : 3
     * videos : [{"url":"http://vfx.mtime.cn/Video/2017/09/11/mp4/170911103724173128.mp4","image":"http://img5.mtime.cn/mg/2017/09/11/103657.86840507.jpg","length":165,"title":"猩球崛起3：终极之战 中国终极预告","videoId":67603},{"url":"http://vfx.mtime.cn/Video/2017/08/08/mp4/170808111311654314.mp4","image":"http://img5.mtime.cn/mg/2017/08/08/111112.85975736.jpg","length":163,"title":"猩球崛起3 人猿对决凯撒版预告","videoId":67010},{"url":"http://vfx.mtime.cn/Video/2017/07/26/mp4/170726151655944219.mp4","image":"http://img5.mtime.cn/mg/2017/07/26/152054.56235770.jpg","length":30,"title":"猩球崛起3：终极之战 中文定档预告片","videoId":66811}]
     * personCount : 50
     */

    var isExpanded:Boolean = false
    var image: String? = null
    var titleCn: String? = null
    var titleEn: String? = null
    var rating: String? = null
    var scoreCount: String? = null
    var year: String? = null
    var content: String? = null
    var runTime: String? = null
    var url: String? = null
    var wapUrl: String? = null
    var isEggHunt: Boolean = false
    var commonSpecial: String? = null
    var hotRanking: Int = 0
    var weekBoxOffice: String? = null
    var totalBoxOffice: String? = null
    var weekBoxOfficeUnit: String? = null
    var totalBoxOfficeUnit: String? = null
    var director: DirectorBean? = null
    var isTicket: Boolean = false
    var showCinemaCount: Int = 0
    var showtimeCount: Int = 0
    var showDay: Int = 0
    var style: StyleBean? = null
    var is3D: Boolean = false
    var isIMAX: Boolean = false
    var isIMAX3D: Boolean = false
    var isDMAX: Boolean = false
    var totalWinAward: Int = 0
    var totalNominateAward: Int = 0
    var release: ReleaseBean? = null
    var imageCount: Int = 0
    var video: String? = null
    var videoId: Int = 0
    var videoCount: Int = 0
    var personCount: Int = 0
    var type: List<String>? = null
    var actorList: List<ActorListBean>? = null
    var festivals: List<*>? = null
    var awards: List<*>? = null
    var directors: List<String>? = null
    var actors: List<String>? = null
    var images: List<String>? = null
    var videos: List<VideosBean>? = null

    class DirectorBean {
        /**
         * directorId : 902708
         * directorName : 马特·里夫斯
         * directorNameEn : Matt Reeves
         * directorImg : http://img21.mtime.cn/ph/2010/05/11/103014.33218315_1280X720X2.jpg
         */

        var directorId: Int = 0
        var directorName: String? = null
        var directorNameEn: String? = null
        var directorImg: String? = null
    }

    class StyleBean {
        /**
         * ifLeadPage : 0
         * leadUrl :
         * leadImag : http://img2.mtime.cn/mg/.jpg
         */

        var ifLeadPage: Int = 0
        var leadUrl: String? = null
        var leadImag: String? = null
    }

    class ReleaseBean {
        /**
         * location : 中国
         * date : 2017-9-15
         */

        var location: String? = null
        var date: String? = null
    }

    class ActorListBean {
        /**
         * actorId : 915215
         * actor : 安迪·瑟金斯
         * actorEn : Andy Serkis
         * actorImg : http://img31.mtime.cn/ph/2016/09/08/174121.83061563_1280X720X2.jpg
         * roleName : 凯撒
         * roleImg : http://img5.mtime.cn/mg/2017/07/17/145517.74820200_80X80X4.jpg
         */

        var actorId: Int = 0
        var actor: String? = null
        var actorEn: String? = null
        var actorImg: String? = null
        var roleName: String? = null
        var roleImg: String? = null
    }

    class VideosBean {
        /**
         * url : http://vfx.mtime.cn/Video/2017/09/11/mp4/170911103724173128.mp4
         * image : http://img5.mtime.cn/mg/2017/09/11/103657.86840507.jpg
         * length : 165
         * title : 猩球崛起3：终极之战 中国终极预告
         * videoId : 67603
         */

        var url: String? = null
        var image: String? = null
        var length: Int = 0
        var title: String? = null
        var videoId: Int = 0
    }

}