package ru.profi.skedda.shared.featues.schedule

data class ScheduleViewState(
    val date: String = "",
    val time: String = "",
    val isLoading: Boolean = true,
    val spaces: List<Space> = mockSpaces
)

class Space(
    val name: String
)

private val mockSpaces = listOf(
    Space("Красная"),
    Space("Поиск талантов"),
    Space("Желтая"),
    Space("Зеленая"),
    Space("Пурпурная"),
    Space("У Тани"),
    Space("Массажная"),
    Space("Красная"),
    Space("Поиск талантов"),
    Space("Желтая"),
    Space("Зеленая"),
    Space("Пурпурная"),
    Space("У Тани"),
    Space("Массажная"),
    Space("Красная"),
    Space("Поиск талантов"),
    Space("Желтая"),
    Space("Зеленая"),
    Space("Пурпурная"),
    Space("У Тани"),
    Space("Массажная"),
    Space("Красная"),
    Space("Поиск талантов"),
    Space("Желтая"),
    Space("Зеленая"),
    Space("Пурпурная"),
    Space("У Тани"),
    Space("Массажная")
)