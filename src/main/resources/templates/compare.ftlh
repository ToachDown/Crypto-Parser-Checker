<#import "common.ftlh" as a>

<@a.page>
    <div class="my-2">
        <a class="btn btn-primary" href="/v1/krypto/home">back</a>
    </div>
    <table class="table table-striped table-dark table-bordered my-2">
        <thead>
            <tr>
                <#list coins as coin>
                    <th class="display-6">${coin.getName()}</th>
                </#list>
            </tr>
        </thead>
        <tbody>
            <tr>
                <#list coins as coin>
                    <th>
                        <ul>
                            <#if coin.price == maxCoin.price>
                                <li class="text-success">
                                    <p >${coin.price} $</p>
                                </li>
                            <#else>
                                <li class="text-warning">
                                    <p>${coin.price} $</p>
                                </li>
                            </#if>
                            <#if coin.dayChange == maxCoin.dayChange>
                                <li class="text-success">
                                    <p >${coin.dayChange} %</p>
                                </li>
                            <#elseif coin.dayChange < maxCoin.dayChange && 0 <coin.dayChange>
                                <li class="text-warning">
                                    <p>${coin.dayChange} %</p>
                                </li>
                            <#else>
                                <li class="text-danger">
                                    <p>${coin.dayChange} %</p>
                                </li>
                            </#if>
                            <li>
                                <p>${coin.cryptoExchange.getName()}</p>
                            </li>
                        </ul>
                    </th>
                </#list>
            </tr>
        </tbody>
    </table>
</@a.page>