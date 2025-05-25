import com.solo.mymovies.data.CustomResult
import com.solo.mymovies.data.remote.api.ApiService
import com.solo.mymovies.data.remote.responseModels.MovieCreditsResponse
import com.solo.mymovies.data.repository.MovieRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryTest {

    private val apiService = mockk<ApiService>()
    private val repository = MovieRepositoryImpl(apiService)

    @Test
    fun `getMovieCredits returns success`() = runBlocking {
        val movieId = 1

        // Mock the API to just return an empty crew response
        coEvery { apiService.getMovieCredits(movieId) } returns MovieCreditsResponse(crew = emptyList())

        // Call repository
        val result = repository.getMovieCredits(movieId).first()

        // Assert result is success and data is empty list
        assertTrue(result is CustomResult.Success && result.data.isEmpty())
    }
}
