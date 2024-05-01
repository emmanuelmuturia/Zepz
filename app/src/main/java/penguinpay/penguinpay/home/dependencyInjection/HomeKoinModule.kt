package penguinpay.penguinpay.home.dependencyInjection

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import penguinpay.penguinpay.home.dataLayer.repository.HomeRepositoryImplementation
import penguinpay.penguinpay.home.domainLayer.repository.HomeRepository
import penguinpay.penguinpay.home.uiLayer.home.HomeScreenViewModel

val homeKoinModule = module {

    single<HomeRepository> {
        HomeRepositoryImplementation()
    }

    viewModel {
        HomeScreenViewModel(
            application = androidApplication(),
            homeRepository = get()
        )
    }

}