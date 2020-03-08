package tml.lab.demoarch.cca.application

import android.content.Context
import tml.lab.demoarch.cca.application.repositories.ProductRepository
import tml.lab.demoarch.cca.infrastructures.datasources.MockProductDataSource
import tml.lab.demoarch.cca.core.viper.ViperContext
import tml.lab.demoarch.ma.repositories.DummyIdeaRepository
import tml.lab.demoarch.ma.repositories.IIdeaRepository

class ServiceLocator {
    companion object {
        fun provideProductRepository(context: ViperContext) : ProductRepository {
            return ProductRepository(MockProductDataSource(context))
        }

        fun provideIdeaRepository(context: Context): IIdeaRepository {
            return DummyIdeaRepository()
        }
    }
}