package org.pih.warehouse.apitablero

import grails.converters.JSON
import grails.plugin.springcache.annotations.Cacheable
import org.pih.warehouse.core.Location
import org.pih.warehouse.core.User
import org.pih.warehouse.tablero.NumberData

class ApitableroController {

    def numberDataService
    def indicatorDataService
    def userService

    def config = {
        User user = User.get(session.user.id)
        def config = userService.getDashboardConfig(user)

        render(config as JSON)
    }

    def updateConfig = {
        User user = User.get(session.user.id)
        def config = userService.updateDashboardConfig(user, request.JSON)

        render(config as JSON)
    }

    @Cacheable("dashboardCache")
    def getInventoryByLotAndBin = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getInventoryByLotAndBin(location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getInProgressShipments = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getInProgressShipments(session.user, location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getInProgressPutaways = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getInProgressPutaways(session.user, location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getReceivingBin = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getReceivingBin(location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getItemsInventoried = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getItemsInventoried(location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getDefaultBin = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getDefaultBin(location)
        render(numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getExpiredProductsInStock = {
        Location location = Location.get(params.locationId)
        NumberData numberData = numberDataService.getExpiredProductsInStock(location)
        render (numberData as JSON)
    }

    @Cacheable("dashboardCache")
    def getExpirationSummary = {
        Location location = Location.get(params.locationId)
        def expirationSummary = indicatorDataService.getExpirationSummaryData(location, params)
        render(expirationSummary.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getFillRate = {
        def fillRate = indicatorDataService.getFillRate()
        render(fillRate.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getInventorySummary = {
        Location location = Location.get(params.locationId)
        def inventorySummary = indicatorDataService.getInventorySummaryData(location)
        render(inventorySummary.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getSentStockMovements = {
        Location location = Location.get(params.locationId)
        def sentStockMovements = indicatorDataService.getSentStockMovements(location, params)
        render(sentStockMovements.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getReceivedStockMovements = {
        Location location = Location.get(params.locationId)
        def receivedStockMovements = indicatorDataService.getReceivedStockData(location, params)
        render(receivedStockMovements.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getOutgoingStock = {
        Location location = Location.get(params.locationId)
        def outgoingStock = indicatorDataService.getOutgoingStock(location)
        render(outgoingStock.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getIncomingStock = {
        Location location = Location.get(params.locationId)
        def incomingStock = indicatorDataService.getIncomingStock(location)
        render(incomingStock.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getDiscrepancy = {
        Location location = Location.get(params.locationId)
        def discrepancy = indicatorDataService.getDiscrepancy(location, params)
        render(discrepancy as JSON)
    }

    @Cacheable("dashboardCache")
    def getDelayedShipments = {
        Location location = Location.get(params.locationId)
        def delayedShipments = indicatorDataService.getDelayedShipments(location)
        render(delayedShipments as JSON)
    }

    @Cacheable("dashboardCache")
    def getProductWithNegativeInventory = {
        Location location = Location.get(params.locationId)
        def productsWithNegativeInventory = numberDataService.getProductWithNegativeInventory(location)
        render(productsWithNegativeInventory as JSON)
    }

    @Cacheable("dashboardCache")
    def getLossCausedByExpiry = {
        Location location = Location.get(params.locationId)
        def lossCausedByExpiry = indicatorDataService.getLossCausedByExpiry(location, params)
        render (lossCausedByExpiry.toJson() as JSON)
    }

    @Cacheable("dashboardCache")
    def getProductsInventoried = {
        Location location = Location.get(params.locationId)
        def productsInventoried = indicatorDataService.getProductsInventoried(location)
        render (productsInventoried.toJson() as JSON)
     }
}
