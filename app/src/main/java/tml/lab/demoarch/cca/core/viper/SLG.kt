package tml.lab.demoarch.cca.core.viper

interface SLGOutput {
    fun d(tag:Any, msg:String)
}

class SLGOutputConsole : SLGOutput {
    override fun d(tag:Any, msg: String) {
        if (tag is String)
            println("SLGOutput: $tag: $msg")
        else
        println("SLGOutput: ${tag.javaClass.name} : $msg")
    }
}

class SLG(var output: SLGOutput? = SLGOutputConsole())  {
    companion object {
        val ins = SLG()
        fun d(tag:Any, msg:String) {
            ins.output?.let {
                it.d(tag, msg)
            }
        }
    }
}