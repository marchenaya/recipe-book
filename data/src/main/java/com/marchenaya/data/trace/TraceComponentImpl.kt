package com.marchenaya.data.trace

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

private const val LOG_FORMAT_LOCAL = "%s; %s; %s; %s"
private const val LOG_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

private const val LOG_ARRAY_PREFIX = "["
private const val LOG_ARRAY_SUFFIX = "]"
private const val LOG_VARIABLE_SEPARATOR = ", "

class TraceComponentImpl @Inject constructor(private val shouldTraceInConsole: Boolean) :
    TraceComponent {

    override fun traceError(id: TraceId, vararg variables: Any) {
        if (shouldTraceInConsole) {
            Log.d(TRACE_COMPONENT_TAG, formatTrace(id, *variables))
        }
    }

    private fun formatTrace(id: TraceId, vararg variables: Any?): String {
        return LOG_FORMAT_LOCAL.format(
            SimpleDateFormat(LOG_DATE_FORMAT, Locale.getDefault()).format(
                Date()
            ),
            id,
            formatVariables(*variables)
        )
    }

    private fun formatVariables(vararg variables: Any?): String {
        val variablesStringBuilder = StringBuilder()
        variables.forEachIndexed { index, variable ->
            when (variable) {
                is Array<*> -> {
                    variablesStringBuilder.append(LOG_ARRAY_PREFIX)
                    variable.forEachIndexed { arrayIndex, arrayItem ->
                        variablesStringBuilder.append(arrayItem.toString())
                        if (arrayIndex != variable.size - 1) {
                            variablesStringBuilder.append(LOG_VARIABLE_SEPARATOR)
                        }
                    }
                    variablesStringBuilder.append(LOG_ARRAY_SUFFIX)
                }

                else -> {
                    variablesStringBuilder.append(variable.toString())
                }
            }
            if (index != variables.size - 1) {
                variablesStringBuilder.append(LOG_VARIABLE_SEPARATOR)
            }
        }
        return variablesStringBuilder.toString()
    }

    private companion object {
        const val TRACE_COMPONENT_TAG = "TRACE_COMPONENT"
    }

}
