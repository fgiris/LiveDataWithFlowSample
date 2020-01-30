/*
 * Copyright (C) 2020 Fatih Giris. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fatih.livedatawithflowsample.ui.weatherforecast.searchcity

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.fatih.livedatawithflowsample.R

class SearchCityAdapter(
    var itemList: List<String>
) : RecyclerView.Adapter<SearchCityAdapter.SearchCityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false) as TextView

        return SearchCityViewHolder(textView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: SearchCityViewHolder, position: Int) {
        holder.textView.text = itemList[position]
    }

    fun setItems(itemList: List<String>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    class SearchCityViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}