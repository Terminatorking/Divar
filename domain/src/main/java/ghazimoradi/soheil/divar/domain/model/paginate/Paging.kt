package ghazimoradi.soheil.divar.domain.model.paginate

data class Paging<T>(
    val content: T,
    val totalPage: Int = 0,
    val totalElements: Long = 0,
    val isFirst: Boolean = true,
    val isLast: Boolean = false,
)

fun <T> Paging<T>.addMore(paging: Paging<T>, content: T): Paging<T> {
    return Paging(
        content = content,
        totalPage = totalPage,
        totalElements = totalElements,
        isFirst = isFirst,
        isLast = isLast
    )
}