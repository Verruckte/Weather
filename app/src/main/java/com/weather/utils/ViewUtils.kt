package com.weather.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun getHeaderPicture(cityName: String) : String {
    when (cityName) {
        "Москва" -> return "https://encrypted-tbn0.gstatic.com/" +
                "images?q=tbn:ANd9GcS39agmod7Z41YlfawxDjJrb8OTe1CcKcMJjQ&usqp=CAU"
        "Санкт-Петербург" -> return "https://st3.depositphotos.com/12780408/16933/v/600/depositphotos_169335692" +
                "-stock-illustration-russia-saint-petersburg-architecture-line.jpg"
        "Новосибирск" -> return "https://st4.depositphotos.com/12780408/25046/v/" +
                "600/depositphotos_250464666-stock-illustration-russia-novosibirsk-flat-landmarks-vector.jpg"
        "Екатеринбург" -> return "https://st4.depositphotos.com/12780408/24143/v/" +
                "600/depositphotos_241433808-stock-illustration-russia-yekaterinburg-city-skyline-architecture.jpg"
        "Нижний Новгород" -> return "https://st3.depositphotos.com/12780408/16939/" +
                "v/600/depositphotos_169396842-stock-illustration-russia-nizhny-novgorod-architecture-line.jpg"
        "Казань" -> return "https://st3.depositphotos.com/1000231/15979/v/600/" +
                "depositphotos_159793736-stock-illustration-kazan-the-most-famous-buildings.jpg"
        "Челябинск" -> return "https://logos.flamingtext.com/City-Logos/" +
                "Chelyabinsk-Amped-Logo.png"
        "Омск" -> return "https://st3.depositphotos.com/1000231/15979/v/600/" +
                "depositphotos_159794614-stock-illustration-omsk-the-most-famous-buildings.jpg"
        "Ростов-на-Дону" -> return "https://st3.depositphotos.com/12780408/16918/v/1600/depositphotos_169189814-stock" +
                "-illustration-russia-rostov-on-don-city.jpg"
        "Саранск" -> return "https://st4.depositphotos.com/12780408/24143/v/950/" +
                "depositphotos_241432704-stock-illustration-russia-mordovia-saransk-city-skyline.jpg"
        "Лондон" -> return "https://lh3.googleusercontent.com/proxy/Ep00pTDTjBgrf4wECjVkXHveFK-_PZZZj4_" +
                "4TVpEmcvnEmfwkaUPEUToJuHo8Zw7V_UmlPmGhXOMrYtErlOUlZNXbsipk1Jf5D8Q233RZX4KJQNkU4IsI7DbX9jUcrNRn3kSIPierR_8TyHW"
        "Токио" -> return "https://st.depositphotos.com/1798004/3504/v/600/" +
                "depositphotos_35049117-stock-illustration-tokyo-big-city-vector-art.jpg"
        "Париж" -> return "https://img.freepik.com/free-vector/cityscape-paris-" +
                "skyline-scene-icon_24908-67063.jpg?size=626&ext=jpg"
        "Берлин" -> return "https://img.freepik.com/free-vector/skyline-of-berlin_23-2147773847.jpg?size=338&ext=jpg"
        "Рим" -> return "https://i0.wp.com/www.techjunkie.com/wp-content/uploads/" +
                "2019/06/Rome-caption.jpg?resize=500%2C217&ssl=1&is-pending-load=1"
        "Минск" -> return "https://st3.depositphotos.com/12780408/19420/v/1600/" +
                "depositphotos_194201358-stock-illustration-belarus-minsk-city-skyline-architecture.jpg"
        "Стамбул" -> return "https://image.freepik.com/free-vector/istanbul-city-" +
                "badge-turkey_66261-13.jpg"
        "Вашингтон" -> return "https://i.ytimg.com/vi/iheyEO-9oTU/maxresdefault.jpg"
        "Киев"-> return "https://i.ytimg.com/vi/gS8GXI-KsR4/maxresdefault.jpg"
        "Пекин" -> return "https://triplinks.ru/wp-content/uploads/2018/01/pekin-beijing.jpg"
        else -> return "https://autogear.ru/misc/i/thumb/n/9/5/8/8/3/1/i/958831.jpg"
    }
}
