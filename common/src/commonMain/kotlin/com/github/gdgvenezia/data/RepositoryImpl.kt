package com.github.gdgvenezia.data

import com.github.gdgvenezia.data.Api
import com.github.gdgvenezia.domain.entities.SocialLinkModel
import com.github.gdgvenezia.domain.Repository
import com.github.gdgvenezia.domain.entities.EventDate
import com.github.gdgvenezia.domain.entities.EventModel
import com.github.gdgvenezia.domain.entities.PhotoModel
import com.github.gdgvenezia.domain.entities.TeamMemberModel

/**
 * @author Andrea Maglie
 */
class RepositoryImpl(private val api: Api): Repository {

    private val event1 = EventModel(title = "Evento 1", date = EventDate(30, 1, 2019, hour = 18, minute = 30, epochInSeconds = 1548869400))
    private val event3 = EventModel(title = "Evento 3", date = EventDate(10, 9, 2019, hour = 19, epochInSeconds = 1568134800))
    private val event2 = EventModel(title = "Evento 2", date = EventDate(5, 6, 2019, hour = 21, epochInSeconds = 1559761200))

    override fun getEventList(): List<EventModel> {
        return listOf(
                event3,
                event2,
                event1
        )
    }

    override fun getPastEventList(): List<EventModel> {
        return listOf(
                event2,
                event1
        )
    }

    override fun getFutureEventList(): List<EventModel> {
        return listOf(event3)
    }

    override suspend fun getPhotoList(): List<PhotoModel> {
        /*
        return listOf(
                PhotoModel(title = "DevFest 2018", tags = listOf("devfest"), url = "https://lh3.googleusercontent.com/Ce4fWZkPbgHo2hOwNRTtSILXUC9G4qLaMxEFZ9X4lNdMFGBG0d8ZK876mtevnwrsyyHRgrB3iL0dPpcz6GxiCrTY2djh1Ho99NWd1dsjv6bJncVeqXP2MM12gc5ieo8_qwNRPK5js1TOnY-WpkGuZ45ErNzxAWepMj-FhQnJieJPF-oZQMibjZTiC4D642bkjDdUs4iLNcLmXF_Jioua1Y5GZwDeJm-DvN6wkq6tmu4fREU1e2p2bLxsgbkDgk3uDseLcQJNjXt9QQMSgVZGi0VdhhmggGPW2aFnBoWnWS__UOLQ3y2HbDlOmgOnF338Ke4J1TEO_cOjQ4AaQHAaeFejxXw5V-JPk3piUO3oYKUo0GhrCq7RIM-9zKzkFBIIQB9bTvosJgeV13MfqxtEr0oE8m4JZWaoZgNTQ_LO9Nx4263s_7LZMI4QcFEpO5RkbYgtFkFa0Vas8xjNH3tlm2fkylbyFfaEhNVoPVsErvPm4hLLKybk3qMvU4f-kRmp5eDlXHWmrGybF0tOCtlfS1LVKDlhVQ7-Pul-5f-sSG_GNiO15ezXDZf_nc5QwuLYiqxv-Vfo66vYBdTNDsgliA5wAMgDYxwZEqX9-DeCNXL0Q22uoSC3Pm6eI83BWqyAgk5XRwmvV7k9HAohlTQyZjLdsXqKMAu2wbzJGgLtTUjAjEV8iTqNDHWq2MGOar9oKvF26MUzZicTIhmVjuyWTgYU=w2444-h1832-no"),
                PhotoModel(title = "DevFest 2018", tags = listOf("devfest"), url = "https://lh3.googleusercontent.com/7dwwKmEQBe8KKbBArvTw2O2aZkOGr2QSmyJIzusHDFgLTV1Otynmr_WoPObHlhL79_l71acbyGSjKXL4AqRJ8xfjgxONXpgUC4U194pr9JiOxwNtOBexIJ03PF4TONc2tNMFr-GGijXxdxpeEYoiUcQKZGiQDh1OpG234HmtXwDa8yWCpCeqUWNpsTKVZ2y2HXNid4FCOq8GkheIqe1mWM5zEbqIxYCpHHz9wm36UuVJprwu08AzoO8xJXKZA0yW7ICEqH5qsOR4ezNVubuATQjs2P675NTtrIC254XCC6nACjkJ4V_vSHZSU5eb0n5_2mUzWZ2QXJtFvkZV4NcT1jAkDMXsFXuB4J5CR7PFkGOOJzHLvkpfU36UceKoa80fYHtEbdgcKxYQFzV-rGkXkPC2lplm_nTGIfb87AEhPhSf5xkirYbg91ATUyZNEvfxPF3WcNPgoUs2W3Eq48y0MjKt0KsG8lL94DVo53fdKDnyevQmRG44MuxLXb-Dy_3NLhFY1VgoYw5j4jrViD3j5OzUs4abSbV9-zxQ2Xnwx5c2Av9G7EOfbTOvWjkG457_FLmHsWJ3U2ETSmJ4kepFnq4pCX1T9xA-3aRziFbkjbCTpgu4a3eTP0yop13E8ESYVn4plSKHqdk8CuCfru9SRdDD9WfFaQ1WEQj1Q-ymn9HQqIpu5gELEvCELUTpg2eL9tH7TxLQeLvfEkOphH5b-R6e7Q=w635-h291-no"),
                PhotoModel(title = "DevFest 2018", tags = listOf("devfest"), url = "https://lh3.googleusercontent.com/kLBpOZhrzJcTdW3wyjOTi7AzxSxdILXqqiAn8ik9vb0DKgVkUQsp-ae0T5fN6jHR7_OYpZ44OSMqXDPv_ACASXgw-R7-VKVy1zzgvsantzhfcHuqAlm9SPhnLs5BGT4HvcNiJdMoQsCNSul8_ijqvxyI3rHUxXGsi48NPfrA-ha7PFaaNGVTRHwqu9LPXsCxZQXPKPn1oI5OTEyizHSOaf3G2wjWaPcwnEQYNwGFi89s7hOCK9D3jOntj9fxHeKo-7B3vLh0teRc2gYG0lzb4upUSRQTnDBmXCebYDu1rApDYM4iWKsLcwSls8eQS97yQaAcypAQftcgrIcRp7a7y1pJARepSXY1yYgo1qOyOHHQJnJ-3CkK7fCr3QXqFoncM-lFCxgvHKSNkG3Dp77kzyhdera3jLkwkwA9ZUdSC9DNLlt8ulPmXcj6WPF9rroWQqP9JGzZbzhwhe5m4rjX6udLqSbkbmWjO7xV39dTnq8-vtCFSmcxWjvjFA9rihoGTL04y8R_1gvDwvaG7gXNLzOWSnHmCUW5cdDDAftxi_QNYyuIpm_vAD_kSHpME0fS_SWOlqsiQxXBDls6N1V-V5oPfEufn_dUkHwuGlpQn36D4MAVGRD9QlRnBBjYhtZmOJb1KorrmDbFi6KcIqbOO9Ja1uoE0nleiAasKu84NAdDrHBUK70ffdUBgzvAAlzhbGbYdei5NSAbw1E_AqqEVVPTqA=w1100-h733-no")
        )
         */

        val photos = api.getPhotos()
        return photos.map { PhotoModel(
                title = it.title,
                url = it.url,
                tags = emptyList()
        ) }
    }

    override fun getTeamMemeberList(): List<TeamMemberModel> {
        return listOf(
                TeamMemberModel(
                    firstname = "Andrea",
                    lastname = "Maglie",
                    pictureUrl = "",
                    shortDescription = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem",
                    longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                    twitterUrl = "https://twitter.com/TechIsFun",
                    linkedinUrl = ""),
                TeamMemberModel(
                        firstname = "Marco",
                        lastname = "Gomiero",
                        pictureUrl = "",
                        shortDescription = "Lorem ipsum",
                        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                        twitterUrl = "https://twitter.com/marcoGomier",
                        linkedinUrl = ""),
                TeamMemberModel(
                        firstname = "Simone",
                        lastname = "Formica",
                        pictureUrl = "",
                        shortDescription = "Consectetur adipiscing elit",
                        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                        twitterUrl = "https://twitter.com/SimoneFormica",
                        linkedinUrl = ""),
                TeamMemberModel(
                        firstname = "Omar",
                        lastname = "Al Bukhari",
                        pictureUrl = "",
                        shortDescription = "Excepteur sint occaecat cupidatat non proident",
                        longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ",
                        twitterUrl = "",
                        linkedinUrl = "https://linkedin.com/in/omar-al-bukhari-01776b111")
                )
    }

    override fun getSocialLinkList(): List<SocialLinkModel> {
        return listOf(
                SocialLinkModel(
                        title = "Facebook",
                        code = "facebook",
                        url = "https://www.facebook.com/gdgvenezia"
                ),
                SocialLinkModel(
                        title = "Twitter",
                        code = "twitter",
                        url = "https://twitter.com/gdgvenezia"
                ),
                SocialLinkModel(
                        title = "YouTube",
                        code = "youtube",
                        url = "https://www.youtube.com/channel/UCnXHsg8plcHc_NuCETV2k6Q"
                ),
                SocialLinkModel(
                        title = "Meetup",
                        code = "meetup",
                        url = "https://www.meetup.com/it-IT/GDG-Venezia"
                ),
                SocialLinkModel(
                        title = "Instagram",
                        code = "instagram",
                        url = "https://www.instagram.com/gdg_venezia"
                ),
                SocialLinkModel(
                        title = "GitHub",
                        code = "github",
                        url = "https://github.com/GDG-Venezia"
                ),
                SocialLinkModel(
                        title = "Telegram",
                        code = "telegram",
                        url = "https://telegram.me/joinchat/CrhySAbNTvs7BZACfpQyyQ"
                ),
                SocialLinkModel(
                        title = "Mail",
                        code = "mail",
                        url = "mailto:veneziagdg@gmail.com"
                )
        )
    }
}