<#import "common.ftlh" as a>

<@a.page>
    <table class="table table-striped">
        <thead>
            <tr>
                <#list CryptoExchanges as crypto>
                    <th>${crypto.name}</th>
                </#list>
            </tr>
        </thead>
        <tbody>
            <tr>
                <#list CryptoExchanges as cryp>
                    <th>
                        <ul>
                            <#list cryp.coins as coin>
                                <li>name: ${coin.name}<br>
                                    price: ${coin.price} $
                                </li>
                            </#list>
                        </ul>
                    </th>
                </#list>
            </tr>
        </tbody>
    </table>
</@a.page>