package penguinpay.penguinpay.home.dataLayer.repository

import penguinpay.penguinpay.home.domainLayer.repository.HomeRepository
import java.math.RoundingMode

class HomeRepositoryImplementation : HomeRepository {
    override fun getReceivableAmount(sendableAmount: String?, selectedCountry: String): String? {
        val countryRate = when(selectedCountry) {
            "Kenya" -> 133.39
            "Nigeria" -> 1399.99
            "Tanzania" -> 2607.29
            "Uganda" -> 3837.96
            else -> 1.00
        }
        return try {
            sendableAmount?.toInt(radix = 2)?.toDouble()?.times(other = countryRate)?.let { result ->
                val roundedResult = result.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
                Integer.toBinaryString(roundedResult.toInt())
            }
        } catch (e: NumberFormatException) {
            null
        }
    }
}