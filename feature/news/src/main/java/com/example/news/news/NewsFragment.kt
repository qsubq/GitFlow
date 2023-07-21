package com.example.news.news

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.core.utils.ErrorDialog
import com.example.core.utils.navigate
import com.example.core.viewModel.NewsListViewModel
import com.example.datamodule.data.localDataSource.repository.LocalRepositoryImpl
import com.example.datamodule.data.remoteDataSource.repository.RemoteRepositoryImpl
import com.example.domain.domain.model.listModel.DomainData
import com.example.domain.domain.useCase.GetNewsFromDataBaseUseCase
import com.example.domain.domain.useCase.GetNewsFromServerUseCase
import com.example.domain.domain.useCase.InsertNewsIntoDataBaseUseCase
import com.example.news.R

class NewsFragment : Fragment() {
    private lateinit var composeView: ComposeView
    private lateinit var viewModel: NewsListViewModel
    private val newsViewModelFactory: NewsViewModelFactory by lazy {
        NewsViewModelFactory(
            requireContext(),
            GetNewsFromServerUseCase(RemoteRepositoryImpl()),
            GetNewsFromDataBaseUseCase(
                LocalRepositoryImpl(
                    requireContext().applicationContext as Application,
                ),
            ),
            InsertNewsIntoDataBaseUseCase(
                LocalRepositoryImpl(
                    requireContext().applicationContext as Application,
                ),
            ),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, newsViewModelFactory)
            .get(NewsListViewModel::class.java)
        viewModel.getListFromServer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        composeView.setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Toolbar()
                MainScreen()
            }
        }
    }

    @Preview
    @Composable
    private fun MainScreen() {
        val mockItems = mutableListOf<DomainData>(DomainData("", "Email", "DFir", 1, "LAastName"))
        val newsState = viewModel.newsList.observeAsState()

        when (newsState.value) {
            is NewsListViewModel.NewsState.IsError -> {
                ErrorDialog(
                    "Error",
                    (newsState.value as NewsListViewModel.NewsState.IsError).error,
                ).show(requireActivity().supportFragmentManager, "ErrorDialog")
            }

            is NewsListViewModel.NewsState.IsLoaded -> {
                val listOfItemsState: NewsListViewModel.NewsState.IsLoaded =
                    newsState.value as NewsListViewModel.NewsState.IsLoaded

                Box(
                    modifier = Modifier.fillMaxWidth().background(Color(0xffe8eded))
                        .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xffffffff)),
                    ) {
                        items(listOfItemsState.list) { item ->
                            Column(
                                modifier = Modifier
                                    .height(400.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        navigate(com.example.core.R.id.action_containerFragment_to_detailNewsFragment)
                                    },
                            ) {
                                AsyncImage(
                                    model = item.avatar,
                                    contentDescription = "Avatar image",
                                    modifier = Modifier
                                        .height(220.dp)
                                        .fillMaxWidth()
                                        .padding(start = 4.dp, end = 4.dp, top = 4.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = item.email,
                                    fontSize = 21.sp,
                                    color = Color(0xff627f8f),
                                    fontFamily = FontFamily(Font(R.font.officinasansextraboldscc)),
                                )
                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.decor_image),
                                    contentDescription = "Decor image",
                                    Modifier
                                        .padding(top = 8.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(top = 10.dp, bottom = 16.dp)
                                        .align(Alignment.CenterHorizontally),
                                    text = item.first_name,
                                    fontSize = 14.sp,
                                    color = Color(0xb3000000),
                                )
                                Box(
                                    Modifier
                                        .background(Color(0xff7db354))
                                        .align(Alignment.CenterHorizontally)
                                        .fillMaxWidth()
                                        .padding(top = 4.dp, bottom = 4.dp),
                                ) {
                                    Row(
                                        Modifier
                                            .background(Color(0xff7db354))
                                            .align(Alignment.Center),
                                    ) {
                                        Image(
                                            imageVector = ImageVector.vectorResource(id = com.example.core.R.drawable.calendar_icon),
                                            contentDescription = "Calendar icon",
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                        Text(
                                            text = item.last_name,
                                            fontSize = 12.sp,
                                            color = Color(0xffffffff),
                                            modifier = Modifier.align(Alignment.CenterVertically),
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                            }
                        }
                    }
                }
            }

            is NewsListViewModel.NewsState.IsLoading -> {
                Box() {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            else -> {
            }
        }
    }

    @Preview
    @Composable
    fun Toolbar() {
        Box(
            modifier = Modifier
                .background(Color(0xff66a636))
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Новости",
                fontSize = 21.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.officinasansextraboldscc)),
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.filter_image),
                contentDescription = "Image back",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 24.dp)
                    .clickable {
                        navigate(R.id.action_containerFragment_to_filterFragment)
                    },
            )
        }
    }
}
