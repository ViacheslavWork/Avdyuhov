package com.example.avdyuhov.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.avdyuhov.common.Result
import com.example.avdyuhov.common.runCatchingResult
import com.example.avdyuhov.data.remote.dto.toMovieItem
import com.example.avdyuhov.domain.model.MovieItem
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Viacheslav Avd on 16.01.2023
 */

class PopularMoviesPagingSource @Inject constructor(
    private val apiService: ApiService,
) : PagingSource<Int, MovieItem>() {

    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.nextKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        delay(1000)
        val page: Int = params.key ?: 1
        val moviesResult = runCatchingResult { apiService.getPopularMovies(page = page) }
        return if (moviesResult is Result.Success) {
            val movies = moviesResult.data.films.map { it.toMovieItem() }
            val maxPages = moviesResult.data.pagesCount
            val nextKey = if (page == maxPages) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(data = movies, prevKey = prevKey, nextKey = nextKey)
        } else
            return LoadResult.Error(IOException())
    }
}