package penguinpay.penguinpay.home.uiLayer.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import penguinpay.penguinpay.commons.domainLayer.state.PenguinPayState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private var _penguinPayState =
        MutableStateFlow<PenguinPayState<Any>>(PenguinPayState.Loading)

    val penguinPayState: StateFlow<PenguinPayState<Any>> =
        _penguinPayState.asStateFlow()

}