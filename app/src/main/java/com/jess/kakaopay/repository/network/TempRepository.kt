package com.jess.kakaopay.repository.network

import com.jess.kakaopay.repository.network.service.TempService

/**
 * @author jess
 * @since 2020.06.12
 */
interface TempRepository {

}

class TempRepositoryImpl constructor(
    private val service: TempService
) : TempRepository {


}
