import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.composetask.utility.NetworkUtil
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NetworkUtilTest {

    private lateinit var context: Context
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var network: Network
    private lateinit var networkCapabilities: NetworkCapabilities

    @Before
    fun setup() {
        context = mockk()
        connectivityManager = mockk()
        network = mockk()
        networkCapabilities = mockk()

        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
    }

    @Test
    fun `test isNetworkAvailable when network available`() {
        // Given
        every { connectivityManager.getActiveNetwork() } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true

        // When
        val result = NetworkUtil.isNetworkAvailable(context)

        // Then
        assertTrue(result)
    }

    @Test
    fun `test isNetworkAvailable when network not available`() {
        // Given
        every { connectivityManager.getActiveNetwork() } returns null

        // When
        val result = NetworkUtil.isNetworkAvailable(context)

        // Then
        assertFalse(result)
    }
}
