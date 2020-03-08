package tml.lab.demoarch

import android.app.Application
import tml.lab.demoarch.cca.application.ServiceLocator
import tml.lab.demoarch.cca.application.repositories.ProductRepository
import tml.lab.demoarch.cca.core.viper.SLG
import tml.lab.demoarch.cca.core.viper.SLGOutputLogcat
import tml.lab.demoarch.cca.core.viper.ViperContext
import tml.lab.demoarch.ma.repositories.IIdeaRepository

class App  : Application() {
    val productRepository: ProductRepository
    get() = ServiceLocator.provideProductRepository(ViperContext( this))

    val ideaRepository: IIdeaRepository
    get() = ServiceLocator.provideIdeaRepository(this)

    override fun onCreate() {
        super.onCreate()
        SLG.ins.output = SLGOutputLogcat()
    }
}