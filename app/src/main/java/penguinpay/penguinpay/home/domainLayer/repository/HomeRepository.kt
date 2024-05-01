package penguinpay.penguinpay.home.domainLayer.repository

fun interface HomeRepository {

    fun getReceivableAmount(sendableAmount: String?, selectedCountry: String): String?

}