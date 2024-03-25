package com.example.karyacakra.common

//sealed class TaskPriority {
//     data object High : TaskPriority()
//    data object Medium : TaskPriority()
//    data object Low : TaskPriority()
//    data object None : TaskPriority()
//    companion object {
//        fun values(): Array<TaskPriority> {
//            return arrayOf(High, Medium, Low, None)
//        }
//
//        fun valueOf(value: String): TaskPriority {
//            val str = value.lowercase()
//            return when (str) {
//                "high" -> High
//                "medium" -> Medium
//                "low" -> Low
//                else -> None
//            }
//        }
//    }
//}

enum class TaskPriority {
    High,
    Medium,
    Low,
    None;

    companion object {

        fun getByName(value: String): TaskPriority {
            val str = value.lowercase()
            return when (str) {
                "high" -> TaskPriority.High
                "medium" -> TaskPriority.Medium
                "low" -> TaskPriority.Low
                else -> None
            }
        }
        fun getAllValues(): List<String> {
            val list = mutableListOf<String>()
                TaskPriority.entries.forEach { list.add(it.name) }
            return list.toList()
        }
    }
}