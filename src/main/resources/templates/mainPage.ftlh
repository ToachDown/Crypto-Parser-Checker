<#import "common.ftlh" as a>

<@a.page>
    <form class="my-2" action="/v1/krypto/home" method="get">
        <div class="my-2 input-group flex-nowrap">
            <input name="filter" type="text" class="form-control" placeholder="search by name" aria-label="find" aria-describedby="addon-wrapping">
        </div>
        <div class="my-2 input-group flex-nowrap">
            <input name="startPrice" type="number" class="form-control" placeholder="start price $" aria-label="Username">
            <span class="input-group-text">-</span>
            <input name="endPrice" type="number" class="form-control" placeholder="end price $" aria-label="Server">
            <button class="btn btn-info" type="submit">find</button>
        </div>
    </form>
    <#if compare??>
    <#else>
        <form action="/v1/krypto/home" method="get">
            <div>
                <input type="hidden" name="compare" value="true">
                <button class="btn btn-primary" type="submit">select to Compare</button>
            </div>
        </form>
    </#if>
    <form action="/v1/krypto/comp" class="my-2">
        <#if compare??>
            <button type="submit" class="btn btn-primary">compare</button>
        </#if>
        <table class="table table-striped table-dark table-bordered my-2">
            <thead>
                <tr>
                    <#list CryptoExchanges as crypto>
                        <th class="display-6 ">${crypto.name}</th>
                    </#list>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <#list CryptoExchanges as cryp>
                        <th>
                            <ul>
                                <#list cryp.coins as coin>
                                    <li class="my-1 row">
                                        <p class="col-4">${coin.name} :</p>
                                        <p class="col-6">${coin.price} $
                                            <#if 0 < coin.dayChange>
                                                <sup class="text-danger">${coin.dayChange} %</sup>
                                            <#else>
                                                <sup class="text-success">${coin.dayChange} %</sup>
                                            </#if>
                                        </p>
                                        <#if compare??>
                                            <input class="col-2" type="checkbox" name="${coin.getName() + '_' + coin.getCryptoExchange().getName()}">
                                        </#if>
                                    </li>
                                </#list>
                            </ul>
                        </th>
                    </#list>
                </tr>
            </tbody>
        </table>
    </form>
</@a.page>