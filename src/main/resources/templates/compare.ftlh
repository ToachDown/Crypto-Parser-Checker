<#import "common.ftlh" as a>

<@a.page>
    <table class="table table-striped table-dark table-bordered my-2">
        <thead>
            <tr>
                <#list coins as coin>
                    <th class="display-6 ">${coin.getName()}</th>
                </#list>
            </tr>
        </thead>
        <tbody>
            <tr>
                <#list coins as coin>
                    <th>
                        <ul>
                            <li>
                                <p>${coin.price} $</p>
                            </li>
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