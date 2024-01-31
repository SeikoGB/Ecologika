package uz.itschool.ecologika.model

enum class Actions (var action:String){
    RESERVES("reserves"),
    PROBLEMS_BY_ID("problem_by_id"),
    INTERESTINGS_BY_ID("interesting_by_id"),
    DO_YOU_KNOW_BY_ID("doyouknow_by_id"),
    FOLKLOR_BY_ID("folklor_by_id"),
    ECO_HISTORY_BY_ID("ecohistory_by_id"),
    QUOTES("quotes"),
    NEWS("news"),
    PROBLEMS("problem"),
    INTERESTINGS("interestings"),
    DO_YOU_KNOWS("doyouknow"),
    ECOHISTORY("ecohistory"),
    FOLKLOR("folklor"),
    MEDIA("photogalleryes"),
    MEDIA_GALLERY("photogallery_by_id")

}