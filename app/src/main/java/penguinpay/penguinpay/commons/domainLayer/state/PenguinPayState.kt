package penguinpay.penguinpay.commons.domainLayer.state

interface PenguinPayState<out T> {

    data object Loading : PenguinPayState<Nothing>

    data class Success<T>(val data: T) : PenguinPayState<T>

    data class Error(val error: Throwable) : PenguinPayState<Nothing>

}