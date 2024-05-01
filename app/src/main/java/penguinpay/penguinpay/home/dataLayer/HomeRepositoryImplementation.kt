package penguinpay.penguinpay.home.dataLayer

import penguinpay.penguinpay.home.domainLayer.HomeRepository

class HomeRepositoryImplementation : HomeRepository {
    override fun getReceivableAmount(sendableAmount: String?, selectedCountry: String): String? {

        val countryRate = when(selectedCountry) {

            "Kenya" -> 133.39

            "Nigeria" -> 361.50

            "Tanzania" -> 2607.29

            "Uganda" -> 3837.96

            else -> 1.00

        }

        return try {
            sendableAmount?.toInt(radix = 2)?.times(other = countryRate.toInt())?.let {
                Integer.toBinaryString(it)
            }
        } catch (e: NumberFormatException) {
            null
        }

    }
}