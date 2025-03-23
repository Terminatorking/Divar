package ghazimoradi.soheil.divar.data.mapper.paginate

import ghazimoradi.soheil.divar.domain.model.paginate.Paging
import ghazimoradi.soheil.divar.network.dto.paginate.PagingResponse

fun <T, R> PagingResponse<T>.toDomain(contentMapper: (T) -> R): Paging<R> {
    return Paging(
        content = contentMapper(content),
        totalPage = totalPage,
        totalElements = totalElements,
        isFirst = isFirst,
        isLast = isLast
    )
}
