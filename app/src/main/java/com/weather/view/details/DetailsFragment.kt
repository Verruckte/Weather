package com.weather.view.details

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import com.weather.model.Weather
import com.weather.utils.showSnackBar
import com.weather.viewmodel.AppState
import com.weather.viewmodel.DetailsViewModel
import com.weather.weather.City
import com.weather.weather.R
import com.weather.weather.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var weatherBundle: Weather
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }
    private lateinit var chosenHeaderPicture: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                setWeather(appState.weatherData[0])
            }
            is AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.includedLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.mainView.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getWeatherFromRemoteSource(
                            weatherBundle.city.lat,
                            weatherBundle.city.lon
                        )
                    })
            }
        }
    }

    private fun setWeather(weather: Weather) {
        with(binding) {
            val city = weatherBundle.city
            saveCity(city, weather)
            cityName.text = city.cityName
            cityCoordinates.text = String.format(
                getString(R.string.city_coordinates),
                city.lat.toString(),
                city.lon.toString()
            )
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            weatherCondition.text = weather.condition

            weather.icon?.let {
                GlideToVectorYou.justLoadImage(
                    activity,
                    Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                    weatherIcon
                )
            }

            getHeaderPicture(city.cityName)
            Picasso
                .get()
                .load(chosenHeaderPicture)
                .into(headerIcon)
        }
    }

    private fun saveCity(city: City, weather: Weather) {
        viewModel.saveCityToDB(
            Weather(
                city,
                weather.temperature,
                weather.feelsLike,
                weather.condition
            )
        )
    }

    private fun getHeaderPicture(cityName: String) {
        when (cityName) {
            "Москва" -> chosenHeaderPicture = "https://encrypted-tbn0.gstatic.com/" +
                    "images?q=tbn:ANd9GcS39agmod7Z41YlfawxDjJrb8OTe1CcKcMJjQ&usqp=CAU"
            "Санкт-Петербург" -> chosenHeaderPicture =
                "https://st3.depositphotos.com/12780408/16933/v/600/depositphotos_169335692" +
                        "-stock-illustration-russia-saint-petersburg-architecture-line.jpg"
            "Новосибирск" -> chosenHeaderPicture = "https://st4.depositphotos.com/12780408/25046/v/" +
                    "600/depositphotos_250464666-stock-illustration-russia-novosibirsk-flat-landmarks-vector.jpg"
            "Екатеринбург" -> chosenHeaderPicture = "https://st4.depositphotos.com/12780408/24143/v/" +
                    "600/depositphotos_241433808-stock-illustration-russia-yekaterinburg-city-skyline-architecture.jpg"
            "Нижний Новгород" -> chosenHeaderPicture = "https://st3.depositphotos.com/12780408/16939/" +
                    "v/600/depositphotos_169396842-stock-illustration-russia-nizhny-novgorod-architecture-line.jpg"
            "Казань" -> chosenHeaderPicture = "https://st3.depositphotos.com/1000231/15979/v/600/" +
                    "depositphotos_159793736-stock-illustration-kazan-the-most-famous-buildings.jpg"
            "Челябинск" -> chosenHeaderPicture = "https://logos.flamingtext.com/City-Logos/" +
                    "Chelyabinsk-Amped-Logo.png"
            "Омск" -> chosenHeaderPicture = "https://st3.depositphotos.com/1000231/15979/v/600/" +
                    "depositphotos_159794614-stock-illustration-omsk-the-most-famous-buildings.jpg"
            "Ростов-на-Дону" -> chosenHeaderPicture =
                "https://st3.depositphotos.com/12780408/16918/v/1600/depositphotos_169189814-stock" +
                        "-illustration-russia-rostov-on-don-city.jpg"
            "Саранск" -> chosenHeaderPicture = "https://st4.depositphotos.com/12780408/24143/v/950/" +
                    "depositphotos_241432704-stock-illustration-russia-mordovia-saransk-city-skyline.jpg"
            "Лондон" -> chosenHeaderPicture =
                "https://lh3.googleusercontent.com/proxy/Ep00pTDTjBgrf4wECjVkXHveFK-_PZZZj4_" +
                        "4TVpEmcvnEmfwkaUPEUToJuHo8Zw7V_UmlPmGhXOMrYtErlOUlZNXbsipk1Jf5D8Q233RZX4KJQNkU4IsI7DbX9jUcrNRn3kSIPierR_8TyHW"
            "Токио" -> chosenHeaderPicture = "https://st.depositphotos.com/1798004/3504/v/600/" +
                    "depositphotos_35049117-stock-illustration-tokyo-big-city-vector-art.jpg"
            "Париж" -> chosenHeaderPicture = "https://img.freepik.com/free-vector/cityscape-paris-" +
                    "skyline-scene-icon_24908-67063.jpg?size=626&ext=jpg"
            "Берлин" -> chosenHeaderPicture =
                "https://img.freepik.com/free-vector/skyline-of-berlin_23-2147773847.jpg?size=338&ext=jpg"
            "Рим" -> chosenHeaderPicture = "https://i0.wp.com/www.techjunkie.com/wp-content/uploads/" +
                    "2019/06/Rome-caption.jpg?resize=500%2C217&ssl=1&is-pending-load=1"
            "Минск" -> chosenHeaderPicture = "https://st3.depositphotos.com/12780408/19420/v/1600/" +
                    "depositphotos_194201358-stock-illustration-belarus-minsk-city-skyline-architecture.jpg"
            "Стамбул" -> chosenHeaderPicture = "https://image.freepik.com/free-vector/istanbul-city-" +
                    "badge-turkey_66261-13.jpg"
            "Вашингтон" -> chosenHeaderPicture =
                "https://i.ytimg.com/vi/iheyEO-9oTU/maxresdefault.jpg"
            "Киев" -> chosenHeaderPicture = "https://i.ytimg.com/vi/gS8GXI-KsR4/maxresdefault.jpg"
            "Пекин" -> chosenHeaderPicture =
                "https://triplinks.ru/wp-content/uploads/2018/01/pekin-beijing.jpg"
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}