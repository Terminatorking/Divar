package ghazimoradi.soheil.divar.domain.usecases.category

import ghazimoradi.soheil.divar.domain.repositories.category.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke() = repository.getCategories()
}