package adaptadores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fragmentos.FragmentoLista
import fragmentos.FragmentoSubCategorias

class AdaptadorViewPager(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    var fragmentList: ArrayList<Fragment>? = null
    var fragmentTitleList: ArrayList<String>? = null

    init {
        fragmentList = ArrayList()
        fragmentTitleList = ArrayList()
    }
    override fun getItem(position: Int): Fragment {
        return fragmentList?.get(position)!!
    }

    override fun getCount(): Int {
        return fragmentList?.size!!
    }
     // esta función la incluiré en la actividad. dentro de configurarViewPager
    fun agregarFragmento (fragment: FragmentoSubCategorias, title: String){
        fragmentList?.add(fragment)!!
        fragmentTitleList?.add(title)!!

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList?.get(position)!!
    }
}