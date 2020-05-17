package com.example.searchbooks.network

import com.squareup.moshi.JsonClass

/**
 * アプリ ー API間のAPI通信で利用するDTO群を定義
 */

/**
 * GetVolumesResponse
 */
@JsonClass(generateAdapter = true)
data class GetVolumesResponse(
    val kind: String?,
    val totalItems: Int?,
    val items: List<GoogleBook>?
)

/**
 * Book
 */
@JsonClass(generateAdapter = true)
data class GoogleBook(
    val kind: String?,
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?,
    val searchInfo: SearchInfo?
)

/**
 * VolumeInfo
 */
@JsonClass(generateAdapter = true)
data class VolumeInfo(
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val readingModes: ReadingModes?,
    val pageCount: Int?,
    val dimensions: Dimensions?,
    val printType: String?,
    val mainCategory: String?,
    val categories: List<String>?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val maturityRating: String?,
    val allowAnonLogging: Boolean?,
    val contentVersion: String?,
    val imageLinks: ImageLink?,
    val language: String?,
    val previewLink: String?,
    val infoLink: String?,
    val canonicalVolumeLink: String?
)

/**
 * SaleInfo
 */
@JsonClass(generateAdapter = true)
data class SaleInfo(
    val country: String?,
    val saleability: String?,
    val onSaleDate: String?,
    val isEbook: Boolean?,
    val listPrice: Price?,
    val retailPrice: Price?,
    val buyLink: String?
)

/**
 * AccessInfo
 */
@JsonClass(generateAdapter = true)
data class AccessInfo(
    val country: String?,
    val viewability: String?,
    val embeddable: Boolean?,
    val publicDomain: Boolean?,
    val textToSpeechPermission: String?,
    val epub: EBook?,
    val pdf: EBook?,
    val webReaderLink: String?,
    val accessViewStatus: String?,
    val quoteSharingAllowed: Boolean?,
    val downloadAccess: DownloadAccess?
)

/**
 * SearchInfo
 */
@JsonClass(generateAdapter = true)
data class SearchInfo(
    val textSnippet: String?
)

/**
 * IndustryIdentifier
 */
@JsonClass(generateAdapter = true)
data class IndustryIdentifier(
    val type: String?,
    val identifier: String?
)

/**
 * ReadingModes
 */
@JsonClass(generateAdapter = true)
data class ReadingModes(
    val text: Boolean?,
    val image: Boolean?
)

/**
 * ImageLink
 */
@JsonClass(generateAdapter = true)
data class ImageLink(
    val smallThumbnail: String?,
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val extraLarge: String?
)

/**
 * Dimensions
 */
@JsonClass(generateAdapter = true)
data class Dimensions(
    val height: String?,
    val width: String?,
    val thickness: String?

)



/**
 * Price
 */
@JsonClass(generateAdapter = true)
data class Price(
    val amount: Double?,
    val currencyCode: String?

)

/**
 * EBook
 */
@JsonClass(generateAdapter = true)
data class EBook(
    val isAvailable: Boolean?,
    val downloadLink: String?,
    val acsTokenLink: String?

)

/**
 * DownloadAccess
 */
@JsonClass(generateAdapter = true)
data class DownloadAccess(
    val kind: String?,
    val volumeId: String?,
    val redirected: Boolean?,
    val deviceAllowed: Boolean?,
    val justAcquired: Boolean?,
    val maxDownloadDevices: Int?,
    val downloadsAcquired: Int?,
    val nonce: String?,
    val source: String?,
    val reasonCode: String?,
    val message: String?,
    val signature: String?
)

