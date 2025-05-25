import app.cash.turbine.test
import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.domain.model.MovieDetails
import com.solo.mymovies.domain.repository.MovieRepository
import com.solo.mymovies.domain.useCase.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMovieDetailsUseCaseTest {

    private val repository: MovieRepository = mockk()
    private lateinit var useCase: GetMovieDetailsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `invoke returns success result`() = runTest(testDispatcher) {
        val movieId = 123
        val fakeMovieDetails = MovieDetails(
            backdropPath = "/fakeBackdrop.jpg",
            budget = 100000000,
            genres = listOf(), // You can add GenreDTO instances if you want
            homepage = "https://fakehomepage.com",
            id = movieId,
            originalTitle = "Fake Original Title",
            overview = "This is a fake movie overview for testing purposes.",
            popularity = 98.7,
            posterPath = "/fakePoster.jpg",
            releaseDate = "2024-01-01",
            status = "Released",
            tagline = "Fake Tagline",
            title = "Fake Movie",
            voteAverage = 7.5,
            voteCount = 1500
        )

        // Mock repository to return success flow
        coEvery { repository.getMovieDetails(movieId) } returns kotlinx.coroutines.flow.flow {
            emit(CustomResult.Success(fakeMovieDetails))
        }

        // Test the use case
        useCase.execute(GetMovieDetailsUseCase.Params(movieId)).test {
            val item = awaitItem()
            assert(item is CustomResult.Success<*>)
            assertEquals(fakeMovieDetails, (item as CustomResult.Success<*>).data)
            awaitComplete()
        }
    }

    @Test
    fun `invoke returns failure result`() = runTest(testDispatcher) {
        val movieId = 123
        val errorMessage = "Network error"

        coEvery { repository.getMovieDetails(movieId) } returns kotlinx.coroutines.flow.flow {
            emit(CustomResult.Failure(errorMessage))
        }

        useCase.execute(GetMovieDetailsUseCase.Params(movieId)).test {
            val item = awaitItem()
            assert(item is CustomResult.Failure)
            assertEquals(errorMessage, (item as CustomResult.Failure).message)
            awaitComplete()
        }
    }
}
