package com.jess.kakaopay.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

/**
 * 영화 Response
 *
 * @author jess
 * @since 2020.06.12
 */
data class MovieData(
    val lastBuildDate: String?,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Item>?
) {

//    @Parcelize
    data class Item(
        val title: String?,
        val link: String?,
        val image: String?,
        val subtitle: String?,
        val pubDate: String?,
        val director: String?,
        val actor: String?,
        val userRating: String?
    ) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            // 직렬화
            parcel.writeString(title)
            parcel.writeString(link)
            parcel.writeString(image)
            parcel.writeString(subtitle)
            parcel.writeString(pubDate)
            parcel.writeString(director)
            parcel.writeString(actor)
            parcel.writeString(userRating)
        }

        override fun describeContents(): Int {
            // ParcelFileDescriptor 사용할때 필요
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel): Item {
                // 역직렬화
                return Item(parcel)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }

    /**
     * 시작 페이지
     *
     * @param displayCount 불러올 페이지 수
     */
    fun getStartNumber(displayCount: Int) = start + displayCount

    /**
     * 다음 페이지 여부
     */
    fun isMorePage() = (start + display) <= total
}