package penguinpay.penguinpay.home.domainLayer

fun interface HomeRepository {

    fun getReceivableAmount(sendableAmount: String?, selectedCountry: String): String?

}