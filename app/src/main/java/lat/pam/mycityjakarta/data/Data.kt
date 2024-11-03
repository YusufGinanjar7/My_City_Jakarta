package com.example.mycityjakarta.data

import com.example.mycity.model.Details
import lat.pam.mycityjakarta.R

object Data {
    val category = listOf(
        R.string.coffee_shop,
        R.string.parks,
        R.string.restaurant,
        R.string.mall,
        R.string.historical_buildings
    )

    fun getCoffeeShop(): List<Details> {
        return listOf(
            Details(
                id = 1,
                titleResourceId = R.string.coffee1,
                logoResourceId = R.drawable.starbucks_logo,
                bannerResourceId = R.drawable.starbucks_banner,
                descriptionResourceId = R.string.starbucks_description
            ),
            Details(
                id = 2,
                titleResourceId = R.string.coffee_bean,
                logoResourceId = R.drawable.coffe_logo03,
                bannerResourceId = R.drawable.coffee_banner03,
                descriptionResourceId = R.string.coffee_bean_description
            ),
            Details(
                id = 3,
                titleResourceId = R.string.love_coffee,
                logoResourceId = R.drawable.coffee_logo04,
                bannerResourceId = R.drawable.cofee_banner04,
                descriptionResourceId = R.string.love_coffee_description
            ),
            Details(
                id = 4,
                titleResourceId = R.string.spell_coffee,
                logoResourceId = R.drawable.coffe_logo02,
                bannerResourceId = R.drawable.coffee_banner02,
                descriptionResourceId = R.string.spell_coffee_description
            ),
            Details(
                id = 5,
                titleResourceId = R.string.coffee_house,
                logoResourceId = R.drawable.coffee_logo01,
                bannerResourceId = R.drawable.coffee_banner01,
                descriptionResourceId = R.string.coffee_house_description
            ),
            Details(
                id = 6,
                titleResourceId = R.string.steamy_cup,
                logoResourceId = R.drawable.coffee_logo05,
                bannerResourceId = R.drawable.coffee_banner05,
                descriptionResourceId = R.string.steamy_cup_description
            )
        )
    }

    fun getParks(): List<Details> {
        return listOf(
            Details(
                id = 7,
                titleResourceId = R.string.monas_park,
                logoResourceId = R.drawable.monas_logo,
                bannerResourceId = R.drawable.monas_banner,
                descriptionResourceId = R.string.monas_park_description
            ),
            Details(
                id = 8,
                titleResourceId = R.string.taman_suropati,
                logoResourceId = R.drawable.taman_suropati_logo,
                bannerResourceId = R.drawable.taman_suropati_banner,
                descriptionResourceId = R.string.taman_suropati_description
            )
        )
    }

    fun getRestaurant(): List<Details> {
        return listOf(
            Details(
                id = 9,
                titleResourceId = R.string.batavia_cafe,
                logoResourceId = R.drawable.batavia_cafe_logo,
                bannerResourceId = R.drawable.batavia_cafe_banner,
                descriptionResourceId = R.string.batavia_cafe_description
            ),
            Details(
                id = 10,
                titleResourceId = R.string.angkasa_restaurant,
                logoResourceId = R.drawable.angkasa_logo,
                bannerResourceId = R.drawable.angkasa_banner,
                descriptionResourceId = R.string.angkasa_restaurant_description
            )
        )
    }

    fun getMall(): List<Details> {
        return listOf(
            Details(
                id = 11,
                titleResourceId = R.string.grand_indonesia,
                logoResourceId = R.drawable.grand_indonesia_logo,
                bannerResourceId = R.drawable.grand_indonesia_banner,
                descriptionResourceId = R.string.grand_indonesia_description
            ),
            Details(
                id = 12,
                titleResourceId = R.string.plaza_senayan,
                logoResourceId = R.drawable.plaza_senayan_logo,
                bannerResourceId = R.drawable.plaza_senayan_banner,
                descriptionResourceId = R.string.plaza_senayan_description
            )
        )
    }

    fun getHistoricalBuildings(): List<Details> {
        return listOf(
            Details(
                id = 13,
                titleResourceId = R.string.kota_tua,
                logoResourceId = R.drawable.kota_tua_logo,
                bannerResourceId = R.drawable.kota_tua_banner,
                descriptionResourceId = R.string.kota_tua_description
            ),
            Details(
                id = 14,
                titleResourceId = R.string.museum_nasional,
                logoResourceId = R.drawable.museum_nasional_logo,
                bannerResourceId = R.drawable.museum_nasional_banner,
                descriptionResourceId = R.string.museum_nasional_description
            )
        )
    }
}
