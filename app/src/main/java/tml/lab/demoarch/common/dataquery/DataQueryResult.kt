package tml.lab.demoarch.common.dataquery

enum class EDataQueryResult { Success, Canceled, Error }
open class DataQueryResult<TData>(var result: EDataQueryResult, var msg: String, var data: TData, var extra: Any? = null)

interface IDataQueryListener<TData> {
    fun onFinished(result: DataQueryResult<TData>)
}