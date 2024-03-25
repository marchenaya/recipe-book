package com.marchenaya.data.trace

interface TraceComponent {

    fun traceError(id: TraceId, vararg variables: Any)

}
