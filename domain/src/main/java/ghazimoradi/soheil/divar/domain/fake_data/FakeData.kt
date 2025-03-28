package ghazimoradi.soheil.divar.domain.fake_data

import ghazimoradi.soheil.divar.domain.model.ads.Ads
import ghazimoradi.soheil.divar.domain.model.ads.AdsSummary
import ghazimoradi.soheil.divar.domain.model.category.Category
import ghazimoradi.soheil.divar.domain.model.image.Image
import ghazimoradi.soheil.divar.domain.model.location.City
import ghazimoradi.soheil.divar.domain.model.location.NeighbourHood
import ghazimoradi.soheil.divar.domain.model.user.User
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

object FakeData {

    fun provideCategories(): ImmutableList<Category> {
        return listOf(
            Category(
                name = "املاک",
                id = 3122,
                icon = "",
                children = listOf(
                    Category(
                        name = "آپارتمان",
                        id = 9604,
                        icon = "civibus",
                        children = listOf()
                    )
                )
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "وسایل نقلیه",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
        ).toImmutableList()
    }

    fun provideAdsSummary(): List<AdsSummary> {
        return listOf(
            AdsSummary(
                id = 71216,
                title = "تانکر آبرسانی و آبرسانی حمل آب شرب و غیر شرب",
                price = "1000000",
                neighbourHood = NeighbourHood(id = 73, name = "بلوار کشاورز"),
                previewImage = Image("https://www.jowhareh.com/images/Jowhareh/galleries_5/large_2fba8ccf-7408-48a4-a4d3-ce2e252ce39b.webp"),
                createAt = "2023-12-30T11:37:56Z"
            ),
            AdsSummary(
                id = 63871,
                title = "Them because site protect job part the.",
                price = "672645",
                neighbourHood = NeighbourHood(id = 53, name = "مرند قدیم"),
                previewImage = null,
                createAt = "2023-08-25T10:27:52Z"
            ),
            AdsSummary(
                id = 62160,
                title = "Theory what music.",
                price = "980461",
                neighbourHood = NeighbourHood(id = 23, name = "قمصر"),
                previewImage = null,
                createAt = "2024-05-16T15:38:38Z"
            ),
            AdsSummary(
                id = 55986,
                title = "Response shoulder think across.",
                price = "761244",
                neighbourHood = NeighbourHood(id = 88, name = "محمدان"),
                previewImage = null,
                createAt = "2024-05-26T04:26:29Z"
            ),
            AdsSummary(
                id = 53323,
                title = "Size draw animal hit short.",
                price = "58611",
                neighbourHood = NeighbourHood(id = 15, name = "ولیعصر"),
                previewImage = null,
                createAt = "2024-07-22T18:16:26Z"
            )
        )
    }

    fun provideAds(): Ads {
        return Ads(
            id = 3595,
            title = "option",
            description = "errem",
            price = "detraxit",
            neighbourHood = NeighbourHood(id = 9534, name = "Jodi Oliver"),
            user = User(
                name = "Nicholas Howell",
                family = "solet",
                email = "denver.odom@example.com",
                token = "nam",
                mobile = "suscipit",
                createAt = null,
                updatedAt = null
            ),
            category = Category(
                name = "Dianne Gibbs",
                id = 7218,
                icon = "percipit",
                children = listOf()
            ),
            images = listOf(),
            answers = listOf(),
            createAt = "",
            updatedAt = ""
        )
    }

    fun provideCities(): List<City> {
        return listOf(
            City(id = 1, name = "تهران", neighbourHoods = listOf()),
            City(id = 2, name = "مشهد", neighbourHoods = listOf()),
            City(id = 3, name = "رشت", neighbourHoods = listOf()),
            City(id = 4, name = "شیراز", neighbourHoods = listOf())
        )
    }
}